package com.volcengine.ark.runtime.model.bot.completion.chat

import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionRequest

class BotChatCompletionRequest : ChatCompletionRequest() {
    /**
     * In bot chat completion, the request.model supposed to be set with botId
     * Or you can pass botId directly, and the request.model will be replaced with it.
     */
    var botId: String? = null

    /**
     * A map to pass extra parameters for bot chat completion.
     */
    private var metadata: Map<String?, Object?>? = null

    fun getMetadata(): Map<String?, Object?>? {
        return metadata
    }

    fun setMetadata(metadata: Map<String?, Object?>?) {
        this.metadata = metadata
    }

    @Override
    fun toString(): String? {
        return "BotChatCompletionRequest{" +
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
                ", botId=" + this.botId +
                ", metadata=" + getMetadata() +
                '}'
    }

    class Builder private constructor() : ChatCompletionRequest.Builder() {
        private var metadata: Map<String?, Object?>? = null
        private var botId: String? = null
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
        private var functions: List<ChatFunction?>? = null
        private var tools: List<ChatTool?>? = null
        private var functionCall: ChatCompletionRequestFunctionCall? = null
        private var logprobs: Boolean? = null
        private var topLogprobs: Integer? = null
        private var n: Integer? = null

        fun metadata(metadata: Map<String?, Object?>?): Builder {
            this.metadata = metadata
            return this
        }

        fun botId(botId: String?): Builder {
            this.botId = botId
            // overwrite the model.
            this.model = botId
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

        fun functions(functions: List<ChatFunction?>?): Builder {
            this.functions = functions
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

        fun n(n: Integer?): Builder {
            this.n = n
            return this
        }

        fun build(): BotChatCompletionRequest {
            val botChatCompletionRequest: BotChatCompletionRequest = com.volcengine.ark.runtime.model.bot.completion.chat.BotChatCompletionRequest()
            botChatCompletionRequest.setMetadata(metadata)
            botChatCompletionRequest.setModel(model)
            botChatCompletionRequest.botId = botId
            botChatCompletionRequest.setMessages(messages)
            botChatCompletionRequest.setTemperature(temperature)
            botChatCompletionRequest.setTopP(topP)
            botChatCompletionRequest.setStream(stream)
            botChatCompletionRequest.setStreamOptions(streamOptions)
            botChatCompletionRequest.setStop(stop)
            botChatCompletionRequest.setMaxTokens(maxTokens)
            botChatCompletionRequest.setPresencePenalty(presencePenalty)
            botChatCompletionRequest.setFrequencyPenalty(frequencyPenalty)
            botChatCompletionRequest.setLogitBias(logitBias)
            botChatCompletionRequest.setUser(user)
            botChatCompletionRequest.setTools(tools)
            botChatCompletionRequest.setFunctionCall(functionCall)
            botChatCompletionRequest.setLogprobs(logprobs)
            botChatCompletionRequest.setTopLogprobs(topLogprobs)
            botChatCompletionRequest.setN(n)
            return botChatCompletionRequest
        }

        companion object {
            fun aBotChatCompletionRequest(): Builder {
                return com.volcengine.ark.runtime.model.bot.completion.chat.BotChatCompletionRequest.Builder()
            }
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.bot.completion.chat.BotChatCompletionRequest.Builder()
        }
    }
}
