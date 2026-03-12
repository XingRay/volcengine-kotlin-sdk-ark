package com.volcengine.ark.runtime.model.completion.chat

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ChatCompletionChoiceLogprobContentTopLogprob {
    /**
     * The message content token.
     */
    var token: String? = null

    /**
     * The log probability of the message content token.
     */
    var logprob: Double = 0.0

    /**
     * A list of integers representing the UTF-8 bytes representation of the token. Useful in instances where characters
     * are represented by multiple tokens and their byte representations must be combined to generate the correct text
     * representation. Can be null if there is no bytes representation for the token.
     */
    var bytes: List<Integer?>? = null

    fun getBytes(): List<Integer?>? {
        return bytes
    }

    fun setBytes(bytes: List<Integer?>?) {
        this.bytes = bytes
    }

    @Override
    fun toString(): String? {
        return "ChatCompletionChoiceLogprobContentTopLogprob{" +
                "token='" + token + '\'' +
                ", logprob=" + logprob +
                ", bytes=" + bytes +
                '}'
    }
}
