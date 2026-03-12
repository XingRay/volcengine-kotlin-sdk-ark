package com.volcengine.ark.runtime.model.responses.usage
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class InputTokensDetails(
    @SerialName("cached_tokens")
    val cachedTokens: Long? = null
)