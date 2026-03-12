package com.volcengine.ark.runtime.service

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter

internal actual fun getCurrentTimestamp(): String {
    val formatter = NSDateFormatter()
    formatter.dateFormat = "yyyy-MM-dd HH:mm:ss.SSS"
    return formatter.stringFromDate(NSDate())
}
