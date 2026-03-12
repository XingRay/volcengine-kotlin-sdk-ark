package com.volcengine.ark.runtime.service

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

/**
 * Ktor 客户端配置
 */
object KtorClientConfig {

    /**
     * 创建默认的 Ktor HttpClient
     */
    fun createDefaultClient(
        apiKey: String? = null,
        timeout: Duration = 10.minutes,
        connectTimeout: Duration = 1.minutes,
        enableLogging: Boolean = false
    ): HttpClient {
        return HttpClient(CIO) {
            // JSON 序列化配置
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = false
                    encodeDefaults = true
                })
            }

            // 超时配置
            install(HttpTimeout) {
                requestTimeoutMillis = timeout.inWholeMilliseconds
                connectTimeoutMillis = connectTimeout.inWholeMilliseconds
                socketTimeoutMillis = timeout.inWholeMilliseconds
            }

            // 日志配置
            if (enableLogging) {
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.INFO
                }
            }

            // 默认请求配置
            defaultRequest {
                // API Key 认证
                apiKey?.let {
                    headers.append("Authorization", "Bearer $it")
                }
                headers.append("Content-Type", "application/json")
            }

            // 重试配置
            install(HttpRequestRetry) {
                retryOnServerErrors(maxRetries = 2)
                exponentialDelay()
            }
        }
    }
}
