package com.volcengine.ark.runtime.model.multimodalembeddings
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class MultimodalEmbeddingUsage(
    /**
     * The number of prompt tokens used.
     */
    @SerialName("prompt_tokens")
    val promptTokens: Long = 0,

    /**
     * The number of total tokens used
     */
    @SerialName("total_tokens")
    val totalTokens: Long = 0,

    @SerialName("prompt_tokens_details")
    val promptTokensDetails: MultimodalEmbeddingPromptTokensDetails? = null
)
