package com.volcengine.ark.runtime.model.context

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class CreateContextRequest {
    @JsonProperty("model")
    var model: String? = null

    @JsonProperty("mode")
    var mode: String? = null

    @JsonProperty("messages")
    private var messages: List<ChatMessage?>? = null

    @JsonProperty("ttl")
    private var ttl: Integer? = null

    @JsonProperty("truncation_strategy")
    private var truncationStrategy: TruncationStrategy? = null

    constructor()

    constructor(model: String?, mode: String?, messages: List<ChatMessage?>?, ttl: Integer?, truncationStrategy: TruncationStrategy?) {
        this.model = model
        this.mode = mode
        this.messages = messages
        this.ttl = ttl
        this.truncationStrategy = truncationStrategy
    }

    fun getMessages(): List<ChatMessage?>? {
        return messages
    }

    fun setMessages(messages: List<ChatMessage?>?) {
        this.messages = messages
    }

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

    @Override
    fun toString(): String? {
        return "CreateContextRequest{" +
                "model='" + model + '\'' +
                ", mode='" + mode + '\'' +
                ", messages=" + messages +
                ", ttl=" + ttl +
                ", truncationStrategy=" + truncationStrategy +
                '}'
    }

    class Builder private constructor() {
        private var model: String? = null
        private var mode: String? = null
        private var messages: List<ChatMessage?>? = null
        private var ttl: Integer? = null
        private var truncationStrategy: TruncationStrategy? = null

        fun model(model: String?): Builder {
            this.model = model
            return this
        }

        fun mode(mode: String?): Builder {
            this.mode = mode
            return this
        }

        fun messages(messages: List<ChatMessage?>?): Builder {
            this.messages = messages
            return this
        }

        fun ttl(ttl: Integer?): Builder {
            this.ttl = ttl
            return this
        }

        fun truncationStrategy(truncationStrategy: TruncationStrategy?): Builder {
            this.truncationStrategy = truncationStrategy
            return this
        }

        fun build(): CreateContextRequest {
            val createContextRequest: CreateContextRequest = com.volcengine.ark.runtime.model.context.CreateContextRequest()
            createContextRequest.model = model
            createContextRequest.mode = mode
            createContextRequest.setMessages(messages)
            createContextRequest.setTtl(ttl)
            createContextRequest.setTruncationStrategy(truncationStrategy)
            return createContextRequest
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.context.CreateContextRequest.Builder()
        }
    }
}
