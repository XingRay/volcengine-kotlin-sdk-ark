package com.volcengine.ark.runtime.model.responses.usage

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class InputTokensDetails {
    @JsonProperty("cached_tokens")
    var cachedTokens: Long? = null

    @Override
    fun toString(): String? {
        return "InputTokensDetails{" +
                "cachedTokens=" + cachedTokens +
                '}'
    }
}