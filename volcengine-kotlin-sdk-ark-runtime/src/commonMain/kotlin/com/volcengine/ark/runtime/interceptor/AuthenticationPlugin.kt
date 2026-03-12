package com.volcengine.ark.runtime.interceptor

import io.ktor.client.*
import io.ktor.client.plugins.api.*
import io.ktor.client.request.*
import io.ktor.util.*

/**
 * Authentication 插件 - 为请求添加 Bearer Token 认证
 */
class AuthenticationPlugin(private val apiKey: String) {

    class Config {
        var apiKey: String? = null
    }

    companion object : ClientPlugin<Config, AuthenticationPlugin> {
        override val key: AttributeKey<AuthenticationPlugin> = AttributeKey("AuthenticationPlugin")

        override fun prepare(block: Config.() -> Unit): AuthenticationPlugin {
            val config = Config().apply(block)
            require(!config.apiKey.isNullOrEmpty()) { "API Key is required" }
            return AuthenticationPlugin(config.apiKey!!)
        }

        override fun install(plugin: AuthenticationPlugin, scope: HttpClient) {
            scope.requestPipeline.intercept(HttpRequestPipeline.State) {
                // 添加 Authorization header
                context.headers.append("Authorization", "Bearer ${plugin.apiKey}")
            }
        }
    }
}

/**
 * 扩展函数：安装 Authentication 插件
 */
fun HttpClientConfig<*>.installAuthentication(apiKey: String) {
    install(AuthenticationPlugin) {
        this.apiKey = apiKey
    }
}
