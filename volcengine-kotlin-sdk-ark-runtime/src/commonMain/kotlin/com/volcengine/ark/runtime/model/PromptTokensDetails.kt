package com.volcengine.ark.runtime.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PromptTokensDetails(
    @SerialName("cached_tokens")
    val cachedTokens: Int? = null,

    @SerialName("provisioned_tokens")
    val provisionedTokens: Int? = null
)