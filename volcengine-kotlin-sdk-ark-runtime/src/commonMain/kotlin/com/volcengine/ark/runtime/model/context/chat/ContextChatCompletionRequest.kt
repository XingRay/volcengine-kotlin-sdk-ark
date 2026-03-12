package com.volcengine.ark.runtime.model.context.chat

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ContextChatCompletionRequest : ChatCompletionRequest() {
    @JsonProperty("context_id")
    var contextId: String? = null

    @Override
    fun toString(): String? {
        return "ContextChatCompletionRequest{" +
                "contextId='" + this.contextId + '\'' +
                "model='" + getModel() + '\'' +
                ", messages=" + getMessages() +
                ", temperature=" + getTemperature() +
                ", topP=" + getTopP() +
                ", stream=" + getStream() +
                ", streamOptions=" + getStreamOptions() +
                ", stop=" + getStop() +
                ", maxTokens=" + getMaxTokens() +
                ", presencePenalty=" + getPresencePenalty() +
                ", frequencyPenalty=" + getFrequencyPenalty() +
                ", logitBias=" + getLogitBias() +
                ", user='" + getUser() + '\'' +
                ", tools=" + getTools() +
                ", functionCall=" + getFunctionCall() +
                ", logprobs=" + getLogprobs() +
                ", topLogprobs=" + getTopLogprobs() +
                '}'
    }


    class Builder private constructor() : ChatCompletionRequest.Builder() {
        private var contextId: String? = null
        private var model: String? = null
        private var messages: List<ChatMessage?>? = null
        private var temperature: Double? = null
        private var topP: Double? = null
        private var stream: Boolean? = null
        private var streamOptions: ChatCompletionRequestStreamOptions? = null
        private var stop: List<String?>? = null
        private var maxTokens: Integer? = null
        private var presencePenalty: Double? = null
        private var frequencyPenalty: Double? = null
        private var logitBias: Map<String?, Integer?>? = null
        private var user: String? = null
        private var tools: List<ChatTool?>? = null
        private var functionCall: ChatCompletionRequestFunctionCall? = null
        private var logprobs: Boolean? = null
        private var topLogprobs: Integer? = null
        private var repetitionPenalty: Double? = null
        private var n: Integer? = null
        private var toolChoice: Object? = null
        private var responseFormat: ChatCompletionRequestResponseFormat? = null

        fun contextId(contextId: String?): Builder {
            this.contextId = contextId
            return this
        }

        fun model(model: String?): Builder {
            this.model = model
            return this
        }

        fun messages(messages: List<ChatMessage?>?): Builder {
            this.messages = messages
            return this
        }

        fun temperature(temperature: Double?): Builder {
            this.temperature = temperature
            return this
        }

        fun topP(topP: Double?): Builder {
            this.topP = topP
            return this
        }

        fun stream(stream: Boolean?): Builder {
            this.stream = stream
            return this
        }

        fun streamOptions(streamOptions: ChatCompletionRequestStreamOptions?): Builder {
            this.streamOptions = streamOptions
            return this
        }

        fun stop(stop: List<String?>?): Builder {
            this.stop = stop
            return this
        }

        fun maxTokens(maxTokens: Integer?): Builder {
            this.maxTokens = maxTokens
            return this
        }

        fun presencePenalty(presencePenalty: Double?): Builder {
            this.presencePenalty = presencePenalty
            return this
        }

        fun frequencyPenalty(frequencyPenalty: Double?): Builder {
            this.frequencyPenalty = frequencyPenalty
            return this
        }

        fun logitBias(logitBias: Map<String?, Integer?>?): Builder {
            this.logitBias = logitBias
            return this
        }

        fun user(user: String?): Builder {
            this.user = user
            return this
        }

        fun tools(tools: List<ChatTool?>?): Builder {
            this.tools = tools
            return this
        }

        fun functionCall(functionCall: ChatCompletionRequestFunctionCall?): Builder {
            this.functionCall = functionCall
            return this
        }

        fun logprobs(logprobs: Boolean?): Builder {
            this.logprobs = logprobs
            return this
        }

        fun topLogprobs(topLogprobs: Integer?): Builder {
            this.topLogprobs = topLogprobs
            return this
        }

        fun repetitionPenalty(repetitionPenalty: Double?): Builder {
            this.repetitionPenalty = repetitionPenalty
            return this
        }

        fun n(n: Integer?): Builder {
            this.n = n
            return this
        }

        fun toolChoice(toolChoice: Object?): Builder {
            this.toolChoice = toolChoice
            return this
        }

        fun responseFormat(responseFormat: ChatCompletionRequestResponseFormat?): Builder {
            this.responseFormat = responseFormat
            return this
        }

        fun build(): ContextChatCompletionRequest {
            val contextChatCompletionRequest: ContextChatCompletionRequest = com.volcengine.ark.runtime.model.context.chat.ContextChatCompletionRequest()
            contextChatCompletionRequest.contextId = contextId
            contextChatCompletionRequest.setModel(model)
            contextChatCompletionRequest.setMessages(messages)
            contextChatCompletionRequest.setTemperature(temperature)
            contextChatCompletionRequest.setTopP(topP)
            contextChatCompletionRequest.setStream(stream)
            contextChatCompletionRequest.setStreamOptions(streamOptions)
            contextChatCompletionRequest.setStop(stop)
            contextChatCompletionRequest.setMaxTokens(maxTokens)
            contextChatCompletionRequest.setPresencePenalty(presencePenalty)
            contextChatCompletionRequest.setFrequencyPenalty(frequencyPenalty)
            contextChatCompletionRequest.setLogitBias(logitBias)
            contextChatCompletionRequest.setUser(user)
            contextChatCompletionRequest.setTools(tools)
            contextChatCompletionRequest.setFunctionCall(functionCall)
            contextChatCompletionRequest.setLogprobs(logprobs)
            contextChatCompletionRequest.setTopLogprobs(topLogprobs)
            contextChatCompletionRequest.setRepetitionPenalty(repetitionPenalty)
            contextChatCompletionRequest.setN(n)
            contextChatCompletionRequest.setToolChoice(toolChoice)
            contextChatCompletionRequest.setResponseFormat(responseFormat)
            return contextChatCompletionRequest
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.context.chat.ContextChatCompletionRequest.Builder()
        }
    }
}
