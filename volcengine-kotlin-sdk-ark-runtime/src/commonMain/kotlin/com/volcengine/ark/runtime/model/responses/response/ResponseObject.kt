package com.volcengine.ark.runtime.model.responses.response

import com.fasterxml.jackson.annotation.JsonProperty

class ResponseObject {
    @JsonProperty("created_at")
    var createdAt: Long? = null

    @JsonProperty("error")
    var error: Error? = null

    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("incomplete_details")
    private var incompleteDetails: IncompleteDetails? = null

    @JsonProperty("max_output_tokens")
    var maxOutputTokens: Long? = null

    @JsonProperty("model")
    var model: String? = null

    @JsonProperty("object")
    var `object`: String? = null
        set(object) {
            field = this.`object`
        }

    @JsonProperty("output")
    private var output: List<BaseItem?>? = null

    @JsonProperty("previous_response_id")
    var previousResponseId: String? = null

    @JsonProperty("thinking")
    private var thinking: ResponsesThinking? = null

    @JsonProperty("reasoning")
    private var reasoning: ResponsesReasoning? = null

    @JsonProperty("service_tier")
    var serviceTier: String? = null

    @JsonProperty("status")
    var status: String? = null

    @JsonProperty("temperature")
    var temperature: Double? = null

    @JsonProperty("tools")
    private var tools: List<ResponsesTool?>? = null

    @JsonProperty("top_p")
    var topP: Double? = null

    @JsonProperty("usage")
    private var usage: Usage? = null

    @JsonProperty("caching")
    private var caching: ResponsesCaching? = null

    @JsonProperty("text")
    private var text: ResponsesText? = null

    @JsonProperty("instructions")
    var instructions: String? = null

    @JsonProperty("store")
    var store: Boolean? = null

    @JsonProperty("expire_at")
    var expireAt: Long? = null

    @JsonProperty("tool_choice")
    private var toolChoice: ResponsesToolChoice? = null

    @JsonProperty("parallel_tool_calls")
    var parallelToolCalls: Boolean? = null

    @JsonProperty("max_tool_calls")
    var maxToolCalls: Long? = null

    fun getIncompleteDetails(): IncompleteDetails? {
        return incompleteDetails
    }

    fun setIncompleteDetails(incompleteDetails: IncompleteDetails?) {
        this.incompleteDetails = incompleteDetails
    }

    fun getOutput(): List<BaseItem?>? {
        return output
    }

    fun setOutput(output: List<BaseItem?>?) {
        this.output = output
    }

    fun getThinking(): ResponsesThinking? {
        return thinking
    }

    fun setThinking(thinking: ResponsesThinking?) {
        this.thinking = thinking
    }

    fun getReasoning(): ResponsesReasoning? {
        return reasoning
    }

    fun setReasoning(reasoning: ResponsesReasoning?) {
        this.reasoning = reasoning
    }

    fun getTools(): List<ResponsesTool?>? {
        return tools
    }

    fun setTools(tools: List<ResponsesTool?>?) {
        this.tools = tools
    }

    fun getUsage(): Usage? {
        return usage
    }

    fun setUsage(usage: Usage?) {
        this.usage = usage
    }

    fun getCaching(): ResponsesCaching? {
        return caching
    }

    fun setCaching(caching: ResponsesCaching?) {
        this.caching = caching
    }

    fun getText(): ResponsesText? {
        return text
    }

    fun setText(text: ResponsesText?) {
        this.text = text
    }

    fun getToolChoice(): ResponsesToolChoice? {
        return toolChoice
    }

    fun setToolChoice(toolChoice: ResponsesToolChoice?) {
        this.toolChoice = toolChoice
    }

    @Override
    fun toString(): String? {
        return "ResponseObject{" +
                "createdAt=" + createdAt +
                ", error=" + error +
                ", id='" + id + '\'' +
                ", incompleteDetails=" + incompleteDetails +
                ", maxOutputTokens=" + maxOutputTokens +
                ", model='" + model + '\'' +
                ", object='" + this.`object` + '\'' +
                ", output=" + output +
                ", previousResponseId='" + previousResponseId + '\'' +
                ", thinking=" + thinking +
                ", reasoning=" + reasoning +
                ", serviceTier='" + serviceTier + '\'' +
                ", status='" + status + '\'' +
                ", temperature=" + temperature +
                ", tools=" + tools +
                ", topP=" + topP +
                ", usage=" + usage +
                ", caching=" + caching +
                ", text=" + text +
                ", instructions='" + instructions + '\'' +
                ", store=" + store +
                ", expireAt=" + expireAt +
                ", toolChoice=" + toolChoice +
                ", parallelToolCalls=" + parallelToolCalls +
                ", maxToolCalls=" + maxToolCalls +
                '}'
    }
}