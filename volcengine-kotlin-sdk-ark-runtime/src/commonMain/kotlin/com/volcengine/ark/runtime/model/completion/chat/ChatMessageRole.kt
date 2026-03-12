package com.volcengine.ark.runtime.model.completion.chat

import com.fasterxml.jackson.annotation.JsonValue

/**
 * see [ChatMessage] documentation.
 */
enum class ChatMessageRole(value: String) {
    SYSTEM("system"),
    USER("user"),
    ASSISTANT("assistant"),
    FUNCTION("function"),
    TOOL("tool");

    @JsonValue
    private val value: String?

    init {
        this.value = value
    }

    fun value(): String? {
        return value
    }
}
