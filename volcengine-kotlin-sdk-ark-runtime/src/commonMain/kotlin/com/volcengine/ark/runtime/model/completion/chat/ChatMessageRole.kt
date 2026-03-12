package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.Serializable


/**
 * see [ChatMessage] documentation.
 */
@Serializable
enum class ChatMessageRole(val value: String) {
    SYSTEM("system"),
    USER("user"),
    ASSISTANT("assistant"),
    FUNCTION("function"),
    TOOL("tool");

}
