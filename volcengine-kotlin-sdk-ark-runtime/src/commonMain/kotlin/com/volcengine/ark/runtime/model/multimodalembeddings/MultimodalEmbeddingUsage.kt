package com.volcengine.ark.runtime.model.multimodalembeddings

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class MultimodalEmbeddingUsage {
    /**
     * The number of prompt tokens used.
     */
    @JsonProperty("prompt_tokens")
    var promptTokens: Long = 0

    /**
     * The number of total tokens used
     */
    @JsonProperty("total_tokens")
    var totalTokens: Long = 0

    @JsonProperty("prompt_tokens_details")
    private var promptTokensDetails: MultimodalEmbeddingPromptTokensDetails? = null

    constructor(promptTokens: Long, completionTokens: Long, totalTokens: Long) {
        this.promptTokens = promptTokens
        this.totalTokens = totalTokens
    }

    constructor(promptTokens: Long, completionTokens: Long, totalTokens: Long, promptTokensDetails: MultimodalEmbeddingPromptTokensDetails?) {
        this.promptTokens = promptTokens
        this.totalTokens = totalTokens
        this.promptTokensDetails = promptTokensDetails
    }

    constructor()

    fun getPromptTokensDetails(): MultimodalEmbeddingPromptTokensDetails? {
        return promptTokensDetails
    }

    fun setPromptTokensDetails(promptTokensDetails: MultimodalEmbeddingPromptTokensDetails?) {
        this.promptTokensDetails = promptTokensDetails
    }

    @Override
    fun toString(): String? {
        return "Usage{" +
                "promptTokens=" + promptTokens +
                ", totalTokens=" + totalTokens +
                ", promptTokensDetails=" + promptTokensDetails +
                '}'
    }
}
