package com.volcengine.ark.runtime.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Usage {
    /**
     * The number of prompt tokens used.
     */
    @JsonProperty("prompt_tokens")
    var promptTokens: Long = 0

    /**
     * The number of completion tokens used.
     */
    @JsonProperty("completion_tokens")
    var completionTokens: Long = 0

    /**
     * The number of total tokens used
     */
    @JsonProperty("total_tokens")
    var totalTokens: Long = 0

    @JsonProperty("prompt_tokens_details")
    private var promptTokensDetails: PromptTokensDetails? = null

    @JsonProperty("completion_tokens_details")
    private var completionTokensDetails: CompletionTokensDetails? = null

    constructor(promptTokens: Long, completionTokens: Long, totalTokens: Long) {
        this.promptTokens = promptTokens
        this.completionTokens = completionTokens
        this.totalTokens = totalTokens
    }

    constructor(promptTokens: Long, completionTokens: Long, totalTokens: Long, promptTokensDetails: PromptTokensDetails?, completionTokensDetails: CompletionTokensDetails?) {
        this.promptTokens = promptTokens
        this.completionTokens = completionTokens
        this.totalTokens = totalTokens
        this.promptTokensDetails = promptTokensDetails
        this.completionTokensDetails = completionTokensDetails
    }

    constructor()

    fun getPromptTokensDetails(): PromptTokensDetails? {
        return promptTokensDetails
    }

    fun setPromptTokensDetails(promptTokensDetails: PromptTokensDetails?) {
        this.promptTokensDetails = promptTokensDetails
    }

    fun getCompletionTokensDetails(): CompletionTokensDetails? {
        return completionTokensDetails
    }

    fun setCompletionTokensDetails(completionTokensDetails: CompletionTokensDetails?) {
        this.completionTokensDetails = completionTokensDetails
    }

    @Override
    fun toString(): String? {
        return "Usage{" +
                "promptTokens=" + promptTokens +
                ", completionTokens=" + completionTokens +
                ", totalTokens=" + totalTokens +
                ", promptTokensDetails=" + promptTokensDetails +
                ", completionTokensDetails=" + completionTokensDetails +
                '}'
    }
}
