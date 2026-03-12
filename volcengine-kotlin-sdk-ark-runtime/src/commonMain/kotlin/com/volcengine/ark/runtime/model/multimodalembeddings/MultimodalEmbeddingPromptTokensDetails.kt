package com.volcengine.ark.runtime.model.multimodalembeddings

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class MultimodalEmbeddingPromptTokensDetails {
    @JsonProperty("text_tokens")
    private var textTokens: Integer? = null

    @JsonProperty("image_tokens")
    private var imageTokens: Integer? = null

    fun getImageTokens(): Integer? {
        return imageTokens
    }

    fun setImageTokens(imageTokens: Integer?) {
        this.imageTokens = imageTokens
    }

    fun getTextTokens(): Integer? {
        return textTokens
    }

    fun setTextTokens(textTokens: Integer?) {
        this.textTokens = textTokens
    }

    @Override
    fun toString(): String? {
        return "PromptTokensDetails{" +
                " textTokens=" + textTokens +
                ", imageTokens=" + imageTokens +
                '}'
    }
}
