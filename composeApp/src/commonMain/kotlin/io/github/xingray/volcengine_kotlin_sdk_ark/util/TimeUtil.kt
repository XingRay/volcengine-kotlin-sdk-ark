package io.github.xingray.volcengine_kotlin_sdk_ark.util

import kotlin.time.Clock

object TimeUtil {
    fun nowMillis(): Long {
        return Clock.System.now().toEpochMilliseconds()
    }
}