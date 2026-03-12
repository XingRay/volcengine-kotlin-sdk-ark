package io.github.xingray.volcengine_kotlin_sdk_ark

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

actual fun getCurrentTimestamp(): String {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
}
