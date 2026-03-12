package com.volcengine.ark.runtime.model.multimodalembeddings
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class MultimodalEmbeddingPromptTokensDetails(
    @SerialName("text_tokens")
    val textTokens: Int? = null,

    @SerialName("image_tokens")
    val imageTokens: Int? = null
)
