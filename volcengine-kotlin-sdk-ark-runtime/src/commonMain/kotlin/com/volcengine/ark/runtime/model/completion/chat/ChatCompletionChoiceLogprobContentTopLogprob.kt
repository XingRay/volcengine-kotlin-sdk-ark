package com.volcengine.ark.runtime.model.completion.chat
import kotlinx.serialization.Serializable


@Serializable
data class ChatCompletionChoiceLogprobContentTopLogprob(
    /**
     * The message content token.
     */
    val token: String? = null,

    /**
     * The log probability of the message content token.
     */
    val logprob: Double = 0.0,

    /**
     * A list of integers representing the UTF-8 bytes representation of the token. Useful in instances where characters
     * are represented by multiple tokens and their byte representations must be combined to generate the correct text
     * representation. Can be null if there is no bytes representation for the token.
     */
    val bytes: List<Int>? = null
)
