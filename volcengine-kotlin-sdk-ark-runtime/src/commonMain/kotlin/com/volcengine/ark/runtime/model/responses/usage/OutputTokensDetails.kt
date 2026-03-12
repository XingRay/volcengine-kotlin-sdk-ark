package com.volcengine.ark.runtime.model.responses.usage

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class OutputTokensDetails {
    @JsonProperty("reasoning_tokens")
    var reasoningTokens: Long? = null
}