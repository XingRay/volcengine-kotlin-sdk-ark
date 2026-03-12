package com.volcengine.ark.runtime.utils

import java.time.Duration

class ModelBreaker {
    private var allowTime: LocalDateTime?

    init {
        this.allowTime = LocalDateTime.now()
    }

    fun Allow(): Boolean {
        return LocalDateTime.now().isAfter(allowTime)
    }

    fun Reset(duration: Duration) {
        this.allowTime = LocalDateTime.now().plusSeconds(duration.getSeconds())
    }

    fun GetAllowedDuration(): Duration {
        val allowDuration: Duration = Duration.between(LocalDateTime.now(), this.allowTime)
        if (allowDuration.isNegative()) {
            return Duration.ZERO
        }
        return allowDuration
    }
}
