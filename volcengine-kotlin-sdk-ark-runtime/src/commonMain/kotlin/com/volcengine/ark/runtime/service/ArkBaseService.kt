package com.volcengine.ark.runtime.service


import java.time.Duration

/**
 * The interface ark service.
 */
object ArkBaseService {
    val BASE_URL: String = "https://ark.cn-beijing.volces.com"
    val BASE_REGION: String = "cn-beijing"
    val DEFAULT_TIMEOUT: Duration? = Duration.ofMinutes(10)
    val DEFAULT_CONNECT_TIMEOUT: Duration? = Duration.ofMinutes(1)
    const val DEFAULT_RETRY_TIMES: Int = 2
}
