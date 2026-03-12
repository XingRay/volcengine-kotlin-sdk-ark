package com.volcengine.ark.runtime.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompletionTokensDetails(
    @SerialName("reasoning_tokens")
    val reasoningTokens: Int? = null,

    @SerialName("provisioned_tokens")
    val provisionedTokens: Int? = null
)
