package com.volcengine.ark.runtime.model.responses.usage
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class OutputTokensDetails(
    @SerialName("reasoning_tokens")
    val reasoningTokens: Long? = null
)