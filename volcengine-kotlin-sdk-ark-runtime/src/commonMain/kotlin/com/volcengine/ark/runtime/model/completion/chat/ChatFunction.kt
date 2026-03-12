package com.volcengine.ark.runtime.model.completion.chat

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ChatFunction {
    /**
     * The name of the function being called.
     */
    var name: String? = null

    /**
     * A description of what the function does, used by the model to choose when and how to call the function.
     */
    var description: String? = null

    /**
     * The parameters the functions accepts.
     */
    @JsonProperty("parameters")
    var parameters: JsonNode? = null

    fun <T> getParameters(cls: Class<T?>?): T? {
        return JacksonUtil.jsonNodeToCls(this.parameters, cls)
    }

    fun getParameters(): JsonNode? {
        return parameters
    }

    fun setParameters(parameters: JsonNode?) {
        this.parameters = parameters
    }

    @Override
    fun toString(): String? {
        return "ChatFunction{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parameters=" + parameters +
                '}'
    }

    class Builder {
        private var name: String? = null
        private var description: String? = null
        private var parameters: JsonNode? = null

        fun name(name: String?): Builder {
            this.name = name
            return this
        }

        fun description(description: String?): Builder {
            this.description = description
            return this
        }

        fun parameters(parameters: Object?): Builder {
            this.parameters = JacksonUtil.clsToJsonNode(parameters)
            return this
        }

        fun build(): ChatFunction {
            val chatFunction: ChatFunction = com.volcengine.ark.runtime.model.completion.chat.ChatFunction()
            chatFunction.name = name
            chatFunction.description = description
            chatFunction.setParameters(parameters)
            return chatFunction
        }
    }
}