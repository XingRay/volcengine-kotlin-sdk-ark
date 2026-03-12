package com.volcengine.ark.runtime.model.completion.chat
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class ChatCompletionChoiceLogprobContent(
    /**
     * The token chosen.
     */
    val token: String? = null,

    /**
     * The log probability of this token, if it is within the top 20 most likely
     * tokens.
     *
     * Otherwise, the value `-9999.0` is used to signify that the token is very
     * unlikely.
     */
    @SerialName("logprob")
    val logprob: Double? = null,

    /**
     * A list of integers representing the UTF-8 bytes representation of the token.
     *
     * Useful in instances where characters are represented by multiple tokens and
     * their byte representations must be combined to generate the correct text
     * representation. Can be `null` if there is no bytes representation for the token.
     */
    @SerialName("bytes")
    val bytes: List<Int>? = null,

    /*
     * The list of most likely tokens and their log probability information, as requested via 'top_logprobs'.
     */
    @SerialName("top_logprobs")
    val topLogprobs: List<ChatCompletionChoiceLogprobContentTopLogprob>? = null
)
