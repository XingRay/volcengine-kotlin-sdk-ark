package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionRequestToolChoice(
    val type: String? = null,
    val function: ChatCompletionRequestToolChoiceFunction? = null
)
