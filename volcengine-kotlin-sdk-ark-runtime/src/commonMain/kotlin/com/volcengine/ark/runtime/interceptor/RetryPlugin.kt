package com.volcengine.ark.runtime.interceptor

import io.ktor.client.plugins.api.*
import kotlinx.coroutines.delay
import kotlin.math.min
import kotlin.math.pow
import kotlin.random.Random

/**
 * Retry 插件配置
 */
class RetryConfig {
    var retryTimes: Int = 2
    var initialRetryDelay: Double = 0.5
    var maxRetryDelay: Double = 8.0
    var maxBatchRetryTimes: Int = 5
    var batchPathPrefix: String = "/api/v3/batch"
}

/**
 * Retry 插件 - 自动重试失败的请求
 */
val RetryPlugin = createClientPlugin("RetryPlugin", ::RetryConfig) {
    val config = pluginConfig

    fun getRetryTimes(path: String): Int {
        return if (path.startsWith(config.batchPathPrefix)) {
            config.maxBatchRetryTimes
        } else {
            config.retryTimes
        }
    }

    fun calculateRetryDelay(max: Int, remain: Int): Long {
        val nbRetries = min(max - remain, (config.maxRetryDelay / config.initialRetryDelay).toInt()).toDouble()
        val sleepSeconds = min(config.initialRetryDelay * 2.0.pow(nbRetries), config.maxRetryDelay)
        val jitter = 1 - 0.25 * Random.nextDouble()
        return (sleepSeconds * jitter * 1000).toLong()
    }

    on(Send) { request ->
        val maxRetries = getRetryTimes(request.url.pathSegments.joinToString("/", prefix = "/"))
        var tryCount = 0
        var lastException: Exception? = null

        while (tryCount <= maxRetries) {
            try {
                val call = proceed(request)
                val response = call.response

                // 检查是否需要重试
                val shouldRetry = response.status.value >= 500 || response.status.value == 429

                if (!shouldRetry || tryCount >= maxRetries) {
                    return@on call
                }

                // 计算重试延迟
                val delayMs = calculateRetryDelay(maxRetries, maxRetries - tryCount)
                delay(delayMs)

                tryCount++
            } catch (e: Exception) {
                lastException = e

                if (tryCount >= maxRetries) {
                    throw e
                }

                // 计算重试延迟
                val delayMs = calculateRetryDelay(maxRetries, maxRetries - tryCount)
                delay(delayMs)

                tryCount++
            }
        }

        // 如果所有重试都失败，抛出最后一个异常
        throw lastException ?: Exception("All retries failed")
    }
}
