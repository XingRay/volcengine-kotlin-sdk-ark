package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * see [ChatMessage] documentation.
 */
@Serializable
enum class ChatMessageRole(val value: String) {
    @SerialName("system")
    SYSTEM("system"),

    @SerialName("user")
    USER("user"),

    @SerialName("assistant")
    ASSISTANT("assistant"),

    @SerialName("function")
    FUNCTION("function"),

    @SerialName("tool")
    TOOL("tool");
}
