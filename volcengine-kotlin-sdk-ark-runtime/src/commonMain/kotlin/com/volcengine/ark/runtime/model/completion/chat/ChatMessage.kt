package com.volcengine.ark.runtime.model.completion.chat

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ChatMessage {
    var role: ChatMessageRole? = null

    var content: Object? = null

    @JsonProperty("reasoning_content")
    var reasoningContent: String? = null

    var name: String? = null

    @JsonProperty("function_call")
    var functionCall: ChatFunctionCall? = null

    @JsonProperty("tool_calls")
    var toolCalls: List<ChatToolCall?>? = null

    @JsonProperty("tool_call_id")
    var toolCallId: String? = null

    fun getRole(): ChatMessageRole? {
        return role
    }

    fun setRole(role: ChatMessageRole?) {
        this.role = role
    }

    fun getContent(): Object? {
        return content
    }

    fun stringContent(): String {
        return String::class.java.cast(content)
    }

    fun multiContent(): List<ChatCompletionContentPart?> {
        return Converter.castList(content, ChatCompletionContentPart::class.java)
    }

    fun setContent(content: Object?) {
        this.content = content
    }


    fun getFunctionCall(): ChatFunctionCall? {
        return functionCall
    }

    fun setFunctionCall(functionCall: ChatFunctionCall?) {
        this.functionCall = functionCall
    }

    fun getToolCalls(): List<ChatToolCall?>? {
        return toolCalls
    }

    fun setToolCalls(toolCalls: List<ChatToolCall?>?) {
        this.toolCalls = toolCalls
    }

    @Override
    fun toString(): String? {
        return "ChatMessage{" +
                "role=" + role +
                ", content=" + content +
                ", reasoningContent='" + reasoningContent + '\'' +
                ", name='" + name + '\'' +
                ", functionCall=" + functionCall +
                ", toolCalls=" + toolCalls +
                ", toolCallId='" + toolCallId + '\'' +
                '}'
    }

    class Builder {
        private var role: ChatMessageRole? = null

        private var content: Object? = null

        @JsonProperty("reasoning_content")
        private var reasoningContent: String? = null

        private var name: String? = null

        @JsonProperty("function_call")
        private var functionCall: ChatFunctionCall? = null

        @JsonProperty("tool_calls")
        private var toolCalls: List<ChatToolCall?>? = null

        @JsonProperty("tool_call_id")
        private var toolCallId: String? = null

        fun role(role: ChatMessageRole?): Builder {
            this.role = role
            return this
        }

        fun content(content: String?): Builder {
            this.content = content
            return this
        }

        fun multiContent(multiContent: List<ChatCompletionContentPart?>?): Builder {
            this.content = multiContent
            return this
        }

        fun name(name: String?): Builder {
            this.name = name
            return this
        }

        fun functionCall(functionCall: ChatFunctionCall?): Builder {
            this.functionCall = functionCall
            return this
        }

        fun toolCalls(toolCalls: List<ChatToolCall?>?): Builder {
            this.toolCalls = toolCalls
            return this
        }

        fun toolCallId(toolCallId: String?): Builder {
            this.toolCallId = toolCallId
            return this
        }

        fun reasoningContent(reasoningContent: String?): Builder {
            this.reasoningContent = reasoningContent
            return this
        }

        fun build(): ChatMessage {
            val chatMessage: ChatMessage = com.volcengine.ark.runtime.model.completion.chat.ChatMessage()
            chatMessage.setRole(role)
            chatMessage.setContent(content)
            chatMessage.reasoningContent = reasoningContent
            chatMessage.name = name
            chatMessage.setFunctionCall(functionCall)
            chatMessage.setToolCalls(toolCalls)
            chatMessage.toolCallId = toolCallId
            return chatMessage
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.completion.chat.ChatMessage.Builder()
        }
    }
}
