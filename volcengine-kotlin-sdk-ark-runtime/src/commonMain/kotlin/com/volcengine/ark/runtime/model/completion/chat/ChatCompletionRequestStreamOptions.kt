package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionRequestStreamOptions(
    @SerialName("include_usage")
    val includeUsage: Boolean? = null,

    @SerialName("chunk_include_usage")
    val chunkIncludeUsage: Boolean? = null,
)
