package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionRequestThinking(
    val type: String? = null,
)
