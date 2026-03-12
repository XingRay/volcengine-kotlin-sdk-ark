package com.volcengine.ark.runtime.utils

class SSE(val data: String?) {
    fun toBytes(): ByteArray {
        return String.format("data: %s\n\n", this.data).getBytes()
    }

    val isDone: Boolean
        get() = com.volcengine.ark.runtime.utils.SSE.Companion.DONE_DATA.equalsIgnoreCase(this.data)

    companion object {
        private val DONE_DATA = "[DONE]"
    }
}