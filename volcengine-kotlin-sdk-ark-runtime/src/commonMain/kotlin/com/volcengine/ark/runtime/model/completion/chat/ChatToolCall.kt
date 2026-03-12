package com.volcengine.ark.runtime.model.completion.chat

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ChatToolCall {
    /**
     * The ID of the tool call
     */
    var id: String? = null

    /**
     * The type of the tool. Currently, only function is supported
     */
    var type: String? = null

    /**
     * The function that the model called
     */
    var function: ChatFunctionCall? = null

    /**
     * The index of the tool call in the list of tool calls
     */
    var index: Integer? = null

    constructor(id: String?, type: String?, function: ChatFunctionCall?) {
        this.id = id
        this.type = type
        this.function = function
    }

    constructor()

    fun getFunction(): ChatFunctionCall? {
        return function
    }

    fun setFunction(function: ChatFunctionCall?) {
        this.function = function
    }

    fun getIndex(): Integer? {
        return index
    }

    fun setIndex(index: Integer?) {
        this.index = index
    }

    @Override
    fun toString(): String? {
        return "ChatToolCall{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", function=" + function +
                ", index=" + index +
                '}'
    }
}
