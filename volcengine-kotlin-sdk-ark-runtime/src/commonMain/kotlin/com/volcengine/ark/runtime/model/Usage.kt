package com.volcengine.ark.runtime.model
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
open class Usage(
    /**
     * The number of prompt tokens used.
     */
    @SerialName("prompt_tokens")
    val promptTokens: Long = 0,

    /**
     * The number of completion tokens used.
     */
    @SerialName("completion_tokens")
    val completionTokens: Long = 0,

    /**
     * The number of total tokens used
     */
    @SerialName("total_tokens")
    val totalTokens: Long = 0,

    @SerialName("prompt_tokens_details")
    val promptTokensDetails: PromptTokensDetails? = null,

    @SerialName("completion_tokens_details")
    val completionTokensDetails: CompletionTokensDetails? = null
)
