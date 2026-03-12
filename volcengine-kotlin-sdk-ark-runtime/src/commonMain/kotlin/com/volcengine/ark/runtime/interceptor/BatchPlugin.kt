package com.volcengine.ark.runtime.interceptor

import com.volcengine.ark.runtime.Const
import com.volcengine.ark.runtime.utils.ModelBreaker
import io.ktor.client.*
import io.ktor.client.plugins.api.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import kotlinx.coroutines.delay
import java.util.concurrent.ConcurrentHashMap
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Batch 插件 - 处理批量请求的速率限制
 */
class BatchPlugin(private val config: Config) {

    data class Config(
        val batchPathPrefix: String = "/api/v3/batch"
    )

    private val modelBreakerMap = ConcurrentHashMap<String, ModelBreaker>()

    companion object : ClientPlugin<Config, BatchPlugin> {
        override val key: AttributeKey<BatchPlugin> = AttributeKey("BatchPlugin")

        override fun prepare(block: Config.() -> Unit): BatchPlugin {
            val config = Config().apply(block)
            return BatchPlugin(config)
        }

        override fun install(plugin: BatchPlugin, scope: HttpClient) {
            scope.plugin(HttpSend).intercept { request ->
                val path = request.url.encodedPath

                // 只处理批量请求
                if (!path.startsWith(plugin.config.batchPathPrefix)) {
                    return@intercept execute(request)
                }

                // 获取模型名称
                val model = request.headers[Const.REQUEST_MODEL]

                // 等待速率限制
                plugin.waitForRetryAfter(model)

                // 执行请求
                val call = execute(request)
                val response = call.response

                // 检查 Retry-After header
                val retryAfter = response.headers["Retry-After"]
                if (!retryAfter.isNullOrEmpty()) {
                    try {
                        val delaySeconds = retryAfter.toInt()
                        plugin.setRetryAfter(model, delaySeconds.seconds)
                    } catch (e: NumberFormatException) {
                        // 无效的 Retry-After header，忽略
                    }
                }

                call
            }
        }
    }

    /**
     * 等待速率限制
     */
    private suspend fun waitForRetryAfter(model: String?) {
        val breaker = getModelBreaker(model)
        while (!breaker.allow()) {
            val duration = breaker.getAllowedDuration()
            if (duration.inWholeSeconds > 0) {
                delay(duration)
            }
        }
    }

    /**
     * 设置速率限制
     */
    private fun setRetryAfter(model: String?, duration: Duration) {
        val breaker = getModelBreaker(model)
        breaker.reset(duration)
    }

    /**
     * 获取或创建 ModelBreaker
     */
    private fun getModelBreaker(model: String?): ModelBreaker {
        val key = model ?: "default"
        return modelBreakerMap.getOrPut(key) { ModelBreaker() }
    }
}

/**
 * 扩展函数：安装 Batch 插件
 */
fun HttpClientConfig<*>.installBatch() {
    install(BatchPlugin)
}
