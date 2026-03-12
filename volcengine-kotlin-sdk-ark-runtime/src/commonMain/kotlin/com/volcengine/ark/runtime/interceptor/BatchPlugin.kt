package com.volcengine.ark.runtime.interceptor

import com.volcengine.ark.runtime.Const
import com.volcengine.ark.runtime.utils.ModelBreaker
import io.ktor.client.plugins.api.*
import io.ktor.client.statement.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Batch 插件配置
 */
class BatchConfig {
    var batchPathPrefix: String = "/api/v3/batch"
}

/**
 * Batch 插件 - 处理批量请求的速率限制
 */
val BatchPlugin = createClientPlugin("BatchPlugin", ::BatchConfig) {
    val batchPathPrefix = pluginConfig.batchPathPrefix
    val modelBreakerMap = mutableMapOf<String, ModelBreaker>()
    val mutex = Mutex()

    suspend fun getModelBreaker(model: String?): ModelBreaker {
        val key = model ?: "default"
        return mutex.withLock {
            modelBreakerMap.getOrPut(key) { ModelBreaker() }
        }
    }

    suspend fun waitForRetryAfter(model: String?) {
        val breaker = getModelBreaker(model)
        while (!breaker.allow()) {
            val duration = breaker.getAllowedDuration()
            if (duration.inWholeSeconds > 0) {
                delay(duration)
            }
        }
    }

    suspend fun setRetryAfter(model: String?, duration: Duration) {
        val breaker = getModelBreaker(model)
        breaker.reset(duration)
    }

    on(Send) { request ->
        val path = request.url.pathSegments.joinToString("/", prefix = "/")

        // 只处理批量请求
        if (path.startsWith(batchPathPrefix).not()) {
            return@on proceed(request)
        }

        // 获取模型名称
        val model = request.headers[Const.REQUEST_MODEL]

        // 等待速率限制
        waitForRetryAfter(model)

        // 执行请求
        val call = proceed(request)

        // 检查 Retry-After header
        val retryAfter = call.response.headers["Retry-After"]
        if (!retryAfter.isNullOrEmpty()) {
            try {
                val delaySeconds = retryAfter.toInt()
                setRetryAfter(model, delaySeconds.seconds)
            } catch (e: NumberFormatException) {
                // 无效的 Retry-After header，忽略
            }
        }

        call
    }
}
