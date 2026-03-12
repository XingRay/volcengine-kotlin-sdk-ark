package com.volcengine.ark.runtime.model.context

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class CreateContextResult {
    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("model")
    var model: String? = null

    @JsonProperty("mode")
    var mode: String? = null

    @JsonProperty("ttl")
    private var ttl: Integer? = null

    @JsonProperty("truncation_strategy")
    private var truncationStrategy: TruncationStrategy? = null

    @JsonProperty("usage")
    private var usage: Usage? = null

    fun getTtl(): Integer? {
        return ttl
    }

    fun setTtl(ttl: Integer?) {
        this.ttl = ttl
    }

    fun getTruncationStrategy(): TruncationStrategy? {
        return truncationStrategy
    }

    fun setTruncationStrategy(truncationStrategy: TruncationStrategy?) {
        this.truncationStrategy = truncationStrategy
    }

    fun getUsage(): Usage? {
        return usage
    }

    fun setUsage(usage: Usage?) {
        this.usage = usage
    }

    @Override
    fun toString(): String? {
        return "CreateContextResult{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", mode='" + mode + '\'' +
                ", ttl=" + ttl +
                ", truncationStrategy=" + truncationStrategy +
                ", usage=" + usage +
                '}'
    }
}
