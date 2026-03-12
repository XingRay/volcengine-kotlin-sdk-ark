package com.volcengine.ark.runtime.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class PromptTokensDetails {
    @JsonProperty("cached_tokens")
    private var cachedTokens: Integer? = null

    @JsonProperty("provisioned_tokens")
    private var provisionedTokens: Integer? = null

    fun getCachedTokens(): Integer? {
        return cachedTokens
    }

    fun setCachedTokens(cachedTokens: Integer?) {
        this.cachedTokens = cachedTokens
    }

    fun getProvisionedTokens(): Integer? {
        return provisionedTokens
    }

    fun setProvisionedTokens(provisionedTokens: Integer?) {
        this.provisionedTokens = provisionedTokens
    }

    @Override
    fun toString(): String? {
        return "PromptTokensDetails{" +
                "cachedTokens=" + cachedTokens +
                ", provisionedTokens=" + provisionedTokens +
                '}'
    }
}