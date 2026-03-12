package com.volcengine.ark.runtime.service

import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes


/**
 * The interface ark service.
 */
object ArkConstants {
    val BASE_URL: String = "https://ark.cn-beijing.volces.com"
    val BASE_REGION: String = "cn-beijing"
    val DEFAULT_TIMEOUT: Duration = 10.minutes
    val DEFAULT_CONNECT_TIMEOUT: Duration = 1.minutes
    const val DEFAULT_RETRY_TIMES: Int = 2
}
