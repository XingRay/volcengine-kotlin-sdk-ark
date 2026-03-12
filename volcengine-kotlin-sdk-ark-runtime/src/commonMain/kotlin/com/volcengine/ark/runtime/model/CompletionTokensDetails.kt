package com.volcengine.ark.runtime.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class CompletionTokensDetails {
    @JsonProperty("reasoning_tokens")
    private var reasoningTokens: Integer? = null

    @JsonProperty("provisioned_tokens")
    private var provisionedTokens: Integer? = null

    fun getReasoningTokens(): Integer? {
        return reasoningTokens
    }

    fun setReasoningTokens(reasoningTokens: Integer?) {
        this.reasoningTokens = reasoningTokens
    }

    fun getProvisionedTokens(): Integer? {
        return provisionedTokens
    }

    fun setProvisionedTokens(provisionedTokens: Integer?) {
        this.provisionedTokens = provisionedTokens
    }

    @Override
    fun toString(): String? {
        return "CompletionTokensDetails{" +
                "reasoningTokens=" + reasoningTokens +
                ", provisionedTokens=" + provisionedTokens +
                '}'
    }
}
