package com.volcengine.ark.runtime.model.completion.chat

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ChatTool {
    /**
     * The type of the tool. Currently, only function is supported.
     */
    var type: String? = null


    var function: ChatFunction? = null

    constructor(type: String?, function: ChatFunction?) {
        this.type = type
        this.function = function
    }

    constructor()

    fun getFunction(): ChatFunction? {
        return function
    }

    fun setFunction(function: ChatFunction?) {
        this.function = function
    }

    @Override
    fun toString(): String? {
        return "ChatTool{" +
                "type='" + type + '\'' +
                ", function=" + function +
                '}'
    }
}