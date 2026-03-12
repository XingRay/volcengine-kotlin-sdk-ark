package com.volcengine.ark.runtime.interceptor

import com.volcengine.ark.runtime.Const
import io.ktor.client.plugins.api.*
import io.ktor.client.request.*
import kotlin.random.Random

/**
 * Request ID 插件配置
 */
class RequestIdConfig {
    var userAgent: String = "volcengine-kotlin-sdk-ark/1.0.0"
}

/**
 * 生成请求 ID
 */
private fun generateRequestId(): String {
    // Use a simple counter-based approach for request ID
    val randomPart = Random.nextBytes(15).joinToString("") { byte ->
        val value = byte.toInt() and 0xFF
        value.toString(16).padStart(2, '0')
    }
    return randomPart
}

/**
 * Request ID 插件 - 为每个请求添加唯一的请求 ID 和 User-Agent
 */
val RequestIdPlugin = createClientPlugin("RequestIdPlugin", ::RequestIdConfig) {
    val userAgent = pluginConfig.userAgent

    onRequest { request, _ ->
        // 添加 Request ID
        if (request.headers[Const.CLIENT_REQUEST_HEADER] == null) {
            request.headers.append(Const.CLIENT_REQUEST_HEADER, generateRequestId())
        }

        // 添加 User-Agent
        request.headers.append("User-Agent", userAgent)
    }
}
