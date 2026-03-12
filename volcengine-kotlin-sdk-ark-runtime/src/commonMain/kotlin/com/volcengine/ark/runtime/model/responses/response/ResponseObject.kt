package com.volcengine.ark.runtime.model.responses.response
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ResponseObject {
    @SerialName("created_at")
    var createdAt: Long? = null

    @SerialName("error")
    var error: Error? = null

    @SerialName("id")
    var id: String? = null

    @SerialName("incomplete_details")
    private var incompleteDetails: IncompleteDetails? = null

    @SerialName("max_output_tokens")
    var maxOutputTokens: Long? = null

    @SerialName("model")
    var model: String? = null

    @SerialName("object")
    var `object`: String? = null
        set(object) {
            field = this.`object`
        }

    @SerialName("output")
    private var output: List<BaseItem?>? = null

    @SerialName("previous_response_id")
    var previousResponseId: String? = null

    @SerialName("thinking")
    private var thinking: ResponsesThinking? = null

    @SerialName("reasoning")
    private var reasoning: ResponsesReasoning? = null

    @SerialName("service_tier")
    var serviceTier: String? = null

    @SerialName("status")
    var status: String? = null

    @SerialName("temperature")
    var temperature: Double? = null

    @SerialName("tools")
    private var tools: List<ResponsesTool?>? = null

    @SerialName("top_p")
    var topP: Double? = null

    @SerialName("usage")
    private var usage: Usage? = null

    @SerialName("caching")
    private var caching: ResponsesCaching? = null

    @SerialName("text")
    private var text: ResponsesText? = null

    @SerialName("instructions")
    var instructions: String? = null

    @SerialName("store")
    var store: Boolean? = null

    @SerialName("expire_at")
    var expireAt: Long? = null

    @SerialName("tool_choice")
    private var toolChoice: ResponsesToolChoice? = null

    @SerialName("parallel_tool_calls")
    var parallelToolCalls: Boolean? = null

    @SerialName("max_tool_calls")
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

}