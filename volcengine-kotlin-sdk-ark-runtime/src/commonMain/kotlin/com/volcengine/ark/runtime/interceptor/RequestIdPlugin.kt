package com.volcengine.ark.runtime.interceptor

import com.volcengine.ark.runtime.Const
import io.ktor.client.*
import io.ktor.client.plugins.api.*
import io.ktor.client.request.*
import io.ktor.util.*
import kotlinx.datetime.Clock
import kotlin.random.Random

/**
 * Request ID 插件 - 为每个请求添加唯一的请求 ID 和 User-Agent
 */
class RequestIdPlugin {

    class Config {
        var userAgent: String = generateUserAgent()
    }

    companion object : ClientPlugin<Config, RequestIdPlugin> {
        override val key: AttributeKey<RequestIdPlugin> = AttributeKey("RequestIdPlugin")

        override fun prepare(block: Config.() -> Unit): RequestIdPlugin {
            return RequestIdPlugin()
        }

        override fun install(plugin: RequestIdPlugin, scope: HttpClient) {
            scope.requestPipeline.intercept(HttpRequestPipeline.State) {
                // 添加 Request ID
                if (context.headers[Const.CLIENT_REQUEST_HEADER] == null) {
                    context.headers.append(Const.CLIENT_REQUEST_HEADER, generateRequestId())
                }

                // 添加 User-Agent
                context.headers.append("User-Agent", generateUserAgent())
            }
        }

        /**
         * 生成请求 ID
         */
        private fun generateRequestId(): String {
            val timestamp = Clock.System.now().toEpochMilliseconds()
            val randomPart = Random.nextBytes(10).joinToString("") {
                "%02x".format(it)
            }
            return "$timestamp$randomPart"
        }

        /**
         * 生成 User-Agent
         */
        private fun generateUserAgent(): String {
            val sdkName = "volcengine-kotlin-sdk-ark"
            val sdkVersion = "1.0.0" // TODO: 从 Version 类获取

            // 获取系统信息
            val osName = System.getProperty("os.name") ?: "Unknown"
            val osVersion = System.getProperty("os.version") ?: "Unknown"
            val javaVersion = System.getProperty("java.version") ?: "Unknown"
            val arch = System.getProperty("os.arch") ?: "Unknown"

            return "$sdkName/$sdkVersion (java-$javaVersion;$osName-$osVersion;$arch)"
        }
    }
}

/**
 * 扩展函数：安装 RequestId 插件
 */
fun HttpClientConfig<*>.installRequestId() {
    install(RequestIdPlugin)
}
