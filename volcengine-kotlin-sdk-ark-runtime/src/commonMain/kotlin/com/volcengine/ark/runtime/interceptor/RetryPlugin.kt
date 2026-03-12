package com.volcengine.ark.runtime.interceptor

import io.ktor.client.*
import io.ktor.client.plugins.api.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.delay
import kotlin.math.min
import kotlin.math.pow
import kotlin.random.Random

/**
 * Retry 插件 - 自动重试失败的请求
 */
class RetryPlugin(private val config: Config) {

    data class Config(
        val retryTimes: Int = 2,
        val initialRetryDelay: Double = 0.5,
        val maxRetryDelay: Double = 8.0,
        val maxBatchRetryTimes: Int = 5,
        val batchPathPrefix: String = "/api/v3/batch"
    )

    companion object : ClientPlugin<Config, RetryPlugin> {
        override val key: AttributeKey<RetryPlugin> = AttributeKey("RetryPlugin")

        override fun prepare(block: Config.() -> Unit): RetryPlugin {
            val config = Config().apply(block)
            return RetryPlugin(config)
        }

        override fun install(plugin: RetryPlugin, scope: HttpClient) {
            scope.plugin(HttpSend).intercept { request ->
                val maxRetries = plugin.getRetryTimes(request.url.encodedPath)
                var tryCount = 0
                var lastException: Exception? = null

                while (tryCount <= maxRetries) {
                    try {
                        val call = execute(request)
                        val response = call.response

                        // 检查是否需要重试
                        val shouldRetry = response.status.value >= 500 || response.status.value == 429

                        if (!shouldRetry || tryCount >= maxRetries) {
                            return@intercept call
                        }

                        // 计算重试延迟
                        val delay = plugin.calculateRetryDelay(maxRetries, maxRetries - tryCount)
                        delay(delay.toLong())

                        tryCount++
                    } catch (e: Exception) {
                        lastException = e

                        if (tryCount >= maxRetries) {
                            throw e
                        }

                        // 计算重试延迟
                        val delay = plugin.calculateRetryDelay(maxRetries, maxRetries - tryCount)
                        delay(delay.toLong())

                        tryCount++
                    }
                }

                // 如果所有重试都失败，抛出最后一个异常
                throw lastException ?: Exception("All retries failed")
            }
        }
    }

    /**
     * 获取重试次数
     */
    private fun getRetryTimes(path: String): Int {
        return if (path.startsWith(config.batchPathPrefix)) {
            config.maxBatchRetryTimes
        } else {
            config.retryTimes
        }
    }

    /**
     * 计算重试延迟（毫秒）
     */
    private fun calculateRetryDelay(max: Int, remain: Int): Long {
        val nbRetries = min(max - remain, (config.maxRetryDelay / config.initialRetryDelay).toInt()).toDouble()
        val sleepSeconds = min(config.initialRetryDelay * 2.0.pow(nbRetries), config.maxRetryDelay)
        val jitter = 1 - 0.25 * Random.nextDouble()
        return (sleepSeconds * jitter * 1000).toLong()
    }
}

/**
 * 扩展函数：安装 Retry 插件
 */
fun HttpClientConfig<*>.installRetry(
    retryTimes: Int = 2,
    initialRetryDelay: Double = 0.5,
    maxRetryDelay: Double = 8.0
) {
    install(RetryPlugin) {
        this.retryTimes = retryTimes
        this.initialRetryDelay = initialRetryDelay
        this.maxRetryDelay = maxRetryDelay
    }
}
