package com.volcengine.ark.runtime.interceptor

import io.ktor.client.plugins.api.*
import io.ktor.client.request.*

/**
 * Authentication 插件配置
 */
class AuthenticationConfig {
    var apiKey: String? = null
}

/**
 * Authentication 插件 - 为请求添加 Bearer Token 认证
 */
val AuthenticationPlugin = createClientPlugin("AuthenticationPlugin", ::AuthenticationConfig) {
    val apiKey = requireNotNull(pluginConfig.apiKey) { "API Key is required" }

    onRequest { request, _ ->
        request.headers.append("Authorization", "Bearer $apiKey")
    }
}
