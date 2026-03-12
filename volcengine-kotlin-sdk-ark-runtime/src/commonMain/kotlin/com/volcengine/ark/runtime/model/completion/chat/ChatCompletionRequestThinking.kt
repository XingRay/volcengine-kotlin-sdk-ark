package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ThinkingType {
    @SerialName("enabled")
    ENABLED,

    @SerialName("disabled")
    DISABLED
}

@Serializable
data class ChatCompletionRequestThinking(
    val type: ThinkingType? = null,
)
