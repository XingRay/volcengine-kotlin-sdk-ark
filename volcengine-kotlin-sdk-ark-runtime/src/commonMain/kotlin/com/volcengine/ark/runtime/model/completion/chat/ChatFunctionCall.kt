package com.volcengine.ark.runtime.model.completion.chat

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ChatFunctionCall {
    /**
     * The name of the function being called
     */
    var name: String? = null

    /**
     * The arguments of the call produced by the model, represented as a JsonNode for easy manipulation.
     */
    var arguments: String? = null

    constructor(name: String?, arguments: String?) {
        this.name = name
        this.arguments = arguments
    }

    constructor()

    @Override
    fun toString(): String? {
        return "ChatFunctionCall{" +
                "name='" + name + '\'' +
                ", arguments=" + arguments +
                '}'
    }
}
