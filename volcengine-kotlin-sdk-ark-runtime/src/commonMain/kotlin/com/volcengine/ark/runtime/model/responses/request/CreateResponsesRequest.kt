package com.volcengine.ark.runtime.model.responses.request
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class CreateResponsesRequest(
    @SerialName("input")
    val input: ResponsesInput? = null,

    @SerialName("model")
    val model: String? = null,

    @SerialName("max_output_tokens")
    val maxOutputTokens: Long? = null,

    @SerialName("previous_response_id")
    val previousResponseId: String? = null,

    @SerialName("thinking")
    val thinking: ResponsesThinking? = null,

    @SerialName("reasoning")
    val reasoning: ResponsesReasoning? = null,

    @SerialName("service_tier")
    val serviceTier: String? = null,

    @SerialName("store")
    val store: Boolean? = null,

    @SerialName("stream")
    val stream: Boolean? = null,

    @SerialName("temperature")
    val temperature: Double? = null,

    @SerialName("tools")
    val tools: List<ResponsesTool?>? = null,

    @SerialName("top_p")
    val topP: Double? = null,

    @SerialName("instructions")
    val instructions: String? = null,

    @SerialName("include")
    val include: List<String?>? = null,

    @SerialName("caching")
    val caching: ResponsesCaching? = null,

    @SerialName("text")
    val text: ResponsesText? = null,

    @SerialName("expire_at")
    val expireAt: Long? = null,

    @SerialName("tool_choice")
    val toolChoice: ResponsesToolChoice? = null,

    @SerialName("parallel_tool_calls")
    val parallelToolCalls: Boolean? = null,

    @SerialName("max_tool_calls")
    val maxToolCalls: Long? = null
) {
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