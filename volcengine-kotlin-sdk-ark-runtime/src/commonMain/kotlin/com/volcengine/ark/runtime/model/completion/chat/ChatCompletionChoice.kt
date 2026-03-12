package com.volcengine.ark.runtime.model.completion.chat

import com.fasterxml.jackson.annotation.JsonAlias

@JsonIgnoreProperties(ignoreUnknown = true)
class ChatCompletionChoice {
    /**
     * This index of this completion in the returned list.
     */
    var index: Integer? = null

    /**
     * The assistant message or delta (when streaming) which was generated
     */
    @JsonAlias("delta")
    var message: ChatMessage? = null

    /**
     * The reason why GPT stopped generating, for example "length".
     */
    @JsonProperty("finish_reason")
    var finishReason: String? = null

    /**
     * The type of content moderation service hit.
     */
    @JsonProperty("moderation_hit_type")
    var moderationHitType: String? = null

    /**
     * Log probability information for the choice.
     */
    @JsonProperty("logprobs")
    var logprobs: ChatCompletionChoiceLogprob? = null

    fun getIndex(): Integer? {
        return index
    }

    fun setIndex(index: Integer?) {
        this.index = index
    }

    fun getMessage(): ChatMessage? {
        return message
    }

    fun setMessage(message: ChatMessage?) {
        this.message = message
    }

    fun getLogprobs(): ChatCompletionChoiceLogprob? {
        return logprobs
    }

    fun setLogprobs(logprobs: ChatCompletionChoiceLogprob?) {
        this.logprobs = logprobs
    }

    @Override
    fun toString(): String? {
        return "ChatCompletionChoice{" +
                "index=" + index +
                ", message=" + message +
                ", finishReason='" + finishReason + '\'' +
                ", moderationHitType='" + moderationHitType + '\'' +
                ", logprobs=" + logprobs +
                '}'
    }
}
