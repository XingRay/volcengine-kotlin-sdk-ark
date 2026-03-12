package com.volcengine.ark.runtime.model.responses.usage

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Usage {
    @JsonProperty("input_tokens")
    var inputTokens: Long? = null

    @JsonProperty("output_tokens")
    var outputTokens: Long? = null

    @JsonProperty("total_tokens")
    var totalTokens: Long? = null

    @JsonProperty("input_tokens_details")
    private var inputTokensDetails: InputTokensDetails? = null

    @JsonProperty("output_tokens_details")
    private var outputTokensDetails: OutputTokensDetails? = null

    @JsonProperty("tool_usage")
    private var toolUsage: Map<String?, Object?>? = null

    @JsonProperty("tool_usage_details")
    private var toolUsageDetails: Map<String?, Object?>? = null

    fun getInputTokensDetails(): InputTokensDetails? {
        return inputTokensDetails
    }

    fun setInputTokensDetails(inputTokensDetails: InputTokensDetails?) {
        this.inputTokensDetails = inputTokensDetails
    }

    fun getOutputTokensDetails(): OutputTokensDetails? {
        return outputTokensDetails
    }

    fun setOutputTokensDetails(outputTokensDetails: OutputTokensDetails?) {
        this.outputTokensDetails = outputTokensDetails
    }

    fun getToolUsage(): Map<String?, Object?>? {
        return toolUsage
    }

    fun setToolUsage(toolUsage: Map<String?, Object?>?) {
        this.toolUsage = toolUsage
    }

    fun getToolUsageDetails(): Map<String?, Object?>? {
        return toolUsageDetails
    }

    fun setToolUsageDetails(toolUsageDetails: Map<String?, Object?>?) {
        this.toolUsageDetails = toolUsageDetails
    }

    @Override
    fun toString(): String? {
        return "Usage{" +
                "inputTokens=" + inputTokens +
                ", outputTokens=" + outputTokens +
                ", totalTokens=" + totalTokens +
                ", inputTokensDetails=" + inputTokensDetails +
                ", outputTokensDetails=" + outputTokensDetails +
                ", toolUsage=" + toolUsage +
                ", toolUsageDetails=" + toolUsageDetails +
                '}'
    }
}