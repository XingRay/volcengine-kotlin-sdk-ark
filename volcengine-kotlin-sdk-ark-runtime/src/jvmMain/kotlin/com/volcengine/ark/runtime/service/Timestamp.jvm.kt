package com.volcengine.ark.runtime.service

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal actual fun getCurrentTimestamp(): String {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
}
