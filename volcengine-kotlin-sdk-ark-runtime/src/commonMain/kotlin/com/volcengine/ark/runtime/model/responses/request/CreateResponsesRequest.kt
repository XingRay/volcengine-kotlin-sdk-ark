package com.volcengine.ark.runtime.model.responses.request

import com.fasterxml.jackson.annotation.JsonProperty

class CreateResponsesRequest {
    @JsonProperty("input")
    private var input: ResponsesInput? = null

    @JsonProperty("model")
    var model: String? = null

    @JsonProperty("max_output_tokens")
    var maxOutputTokens: Long? = null

    @JsonProperty("previous_response_id")
    var previousResponseId: String? = null

    @JsonProperty("thinking")
    private var thinking: ResponsesThinking? = null

    @JsonProperty("reasoning")
    private var reasoning: ResponsesReasoning? = null

    @JsonProperty("service_tier")
    var serviceTier: String? = null

    @JsonProperty("store")
    var store: Boolean? = null

    @JsonProperty("stream")
    var stream: Boolean? = null

    @JsonProperty("temperature")
    var temperature: Double? = null

    @JsonProperty("tools")
    private var tools: List<ResponsesTool?>? = null

    @JsonProperty("top_p")
    var topP: Double? = null

    @JsonProperty("instructions")
    var instructions: String? = null

    @JsonProperty("include")
    var include: List<String?>? = null

    @JsonProperty("caching")
    private var caching: ResponsesCaching? = null

    @JsonProperty("text")
    private var text: ResponsesText? = null

    @JsonProperty("expire_at")
    var expireAt: Long? = null

    @JsonProperty("tool_choice")
    private var toolChoice: ResponsesToolChoice? = null

    @JsonProperty("parallel_tool_calls")
    var parallelToolCalls: Boolean? = null

    @JsonProperty("max_tool_calls")
    var maxToolCalls: Long? = null

    fun getInput(): ResponsesInput? {
        return input
    }

    fun setInput(input: ResponsesInput?) {
        this.input = input
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
        return "ResponsesRequest{" +
                "input=" + input +
                ", model='" + model + '\'' +
                ", maxOutputTokens=" + maxOutputTokens +
                ", previousResponseId='" + previousResponseId + '\'' +
                ", thinking=" + thinking +
                ", reasoning=" + reasoning +
                ", serviceTier='" + serviceTier + '\'' +
                ", store=" + store +
                ", stream=" + stream +
                ", temperature=" + temperature +
                ", tools=" + tools +
                ", topP=" + topP +
                ", instructions='" + instructions + '\'' +
                ", include=" + include +
                ", caching=" + caching +
                ", text=" + text +
                ", expireAt=" + expireAt +
                ", toolChoice=" + toolChoice +
                ", parallelToolCalls=" + parallelToolCalls +
                ", maxToolCalls=" + maxToolCalls +
                '}'
    }

    class Builder {
        private var input: ResponsesInput? = null
        private var model: String? = null
        private var maxOutputTokens: Long? = null
        private var previousResponseId: String? = null
        private var thinking: ResponsesThinking? = null
        private var reasoning: ResponsesReasoning? = null
        private var serviceTier: String? = null
        private var store: Boolean? = null
        private var stream: Boolean? = null
        private var temperature: Double? = null
        private var tools: List<ResponsesTool?>? = null
        private var topP: Double? = null
        private var instructions: String? = null
        private var include: List<String?>? = null
        private var caching: ResponsesCaching? = null
        private var text: ResponsesText? = null
        private var expireAt: Long? = null
        private var toolChoice: ResponsesToolChoice? = null
        private var parallelToolCalls: Boolean? = null
        private var maxToolCalls: Long? = null

        fun input(input: ResponsesInput?): Builder {
            this.input = input
            return this
        }

        fun model(model: String?): Builder {
            this.model = model
            return this
        }

        fun maxOutputTokens(maxOutputTokens: Long?): Builder {
            this.maxOutputTokens = maxOutputTokens
            return this
        }

        fun previousResponseId(previousResponseId: String?): Builder {
            this.previousResponseId = previousResponseId
            return this
        }

        fun thinking(thinking: ResponsesThinking?): Builder {
            this.thinking = thinking
            return this
        }

        fun reasoning(reasoning: ResponsesReasoning?): Builder {
            this.reasoning = reasoning
            return this
        }

        fun serviceTier(serviceTier: String?): Builder {
            this.serviceTier = serviceTier
            return this
        }

        fun store(store: Boolean?): Builder {
            this.store = store
            return this
        }

        fun stream(stream: Boolean?): Builder {
            this.stream = stream
            return this
        }

        fun temperature(temperature: Double?): Builder {
            this.temperature = temperature
            return this
        }

        fun tools(tools: List<ResponsesTool?>?): Builder {
            this.tools = tools
            return this
        }

        fun topP(topP: Double?): Builder {
            this.topP = topP
            return this
        }

        fun instructions(instructions: String?): Builder {
            this.instructions = instructions
            return this
        }

        fun include(include: List<String?>?): Builder {
            this.include = include
            return this
        }

        fun caching(caching: ResponsesCaching?): Builder {
            this.caching = caching
            return this
        }

        fun text(text: ResponsesText?): Builder {
            this.text = text
            return this
        }

        fun expireAt(expireAt: Long?): Builder {
            this.expireAt = expireAt
            return this
        }

        fun toolChoice(toolChoice: ResponsesToolChoice?): Builder {
            this.toolChoice = toolChoice
            return this
        }

        fun parallelToolCalls(parallelToolCalls: Boolean?): Builder {
            this.parallelToolCalls = parallelToolCalls
            return this
        }

        fun maxToolCalls(maxToolCalls: Long?): Builder {
            this.maxToolCalls = maxToolCalls
            return this
        }

        fun build(): CreateResponsesRequest {
            val responsesRequest: CreateResponsesRequest = com.volcengine.ark.runtime.model.responses.request.CreateResponsesRequest()
            responsesRequest.setInput(input)
            responsesRequest.model = model
            responsesRequest.maxOutputTokens = maxOutputTokens
            responsesRequest.previousResponseId = previousResponseId
            responsesRequest.setThinking(thinking)
            responsesRequest.setReasoning(reasoning)
            responsesRequest.serviceTier = serviceTier
            responsesRequest.store = store
            responsesRequest.stream = stream
            responsesRequest.temperature = temperature
            responsesRequest.setTools(tools)
            responsesRequest.topP = topP
            responsesRequest.instructions = instructions
            responsesRequest.include = include
            responsesRequest.setCaching(caching)
            responsesRequest.setText(text)
            responsesRequest.expireAt = expireAt
            responsesRequest.setToolChoice(toolChoice)
            responsesRequest.parallelToolCalls = parallelToolCalls
            responsesRequest.maxToolCalls = maxToolCalls
            return responsesRequest
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.request.CreateResponsesRequest.Builder()
        }
    }
}