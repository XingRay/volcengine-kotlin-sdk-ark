package com.volcengine.ark.runtime.model.completion.chat

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ChatCompletionChoiceLogprobContent {
    /**
     * The token chosen.
     */
    var token: String? = null

    /**
     * The log probability of this token, if it is within the top 20 most likely
     * tokens.
     * 
     * Otherwise, the value `-9999.0` is used to signify that the token is very
     * unlikely.
     */
    @JsonProperty("logprob")
    var logprob: Double? = null

    /**
     * A list of integers representing the UTF-8 bytes representation of the token.
     * 
     * Useful in instances where characters are represented by multiple tokens and
     * their byte representations must be combined to generate the correct text
     * representation. Can be `null` if there is no bytes representation for the token.
     */
    @JsonProperty("bytes")
    var bytes: List<Integer?>? = null

    /*
     * The list of most likely tokens and their log probability information, as requested via 'top_logprobs'.
     */
    @JsonProperty("top_logprobs")
    var topLogprobs: List<ChatCompletionChoiceLogprobContentTopLogprob?>? = null

    fun getBytes(): List<Integer?>? {
        return bytes
    }

    fun setBytes(bytes: List<Integer?>?) {
        this.bytes = bytes
    }

    fun getTopLogprobs(): List<ChatCompletionChoiceLogprobContentTopLogprob?>? {
        return topLogprobs
    }

    fun setTopLogprobs(topLogprobs: List<ChatCompletionChoiceLogprobContentTopLogprob?>?) {
        this.topLogprobs = topLogprobs
    }

    @Override
    fun toString(): String? {
        return "ChatCompletionChoiceLogprobContent{" +
                "token='" + token + '\'' +
                ", logprob=" + logprob +
                ", bytes=" + bytes +
                ", topLogprobs=" + topLogprobs +
                '}'
    }
}
