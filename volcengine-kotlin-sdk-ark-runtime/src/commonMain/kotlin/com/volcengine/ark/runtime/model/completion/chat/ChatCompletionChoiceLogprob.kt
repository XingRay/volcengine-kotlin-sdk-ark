package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.Serializable


@Serializable
data class ChatCompletionChoiceLogprob(
    val content: List<ChatCompletionChoiceLogprobContent>? = null
)
