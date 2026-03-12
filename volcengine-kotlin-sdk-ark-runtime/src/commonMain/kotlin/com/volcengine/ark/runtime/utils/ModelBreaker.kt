package com.volcengine.ark.runtime.utils

import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.Instant

/**
 * 模型速率限制断路器
 * 用于处理批量请求的 Retry-After 限制
 */
class ModelBreaker {
    private var allowTime: Instant = Clock.System.now()

    /**
     * 检查是否允许执行请求
     */
    fun allow(): Boolean {
        return Clock.System.now() >= allowTime
    }

    /**
     * 重置允许时间
     */
    fun reset(duration: Duration) {
        allowTime = Clock.System.now() + duration
    }

    /**
     * 获取距离允许时间的剩余时长
     */
    fun getAllowedDuration(): Duration {
        val now = Clock.System.now()
        val remaining = allowTime - now
        return if (remaining.isNegative()) Duration.ZERO else remaining
    }
}
