package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ChatMessage(
    val role: ChatMessageRole? = null,

    val content: ChatMessageContent? = null,

    @SerialName("reasoning_content")
    val reasoningContent: String? = null,

    val name: String? = null,

    @SerialName("function_call")
    val functionCall: ChatFunctionCall? = null,

    @SerialName("tool_calls")
    val toolCalls: List<ChatToolCall?>? = null,

    @SerialName("tool_call_id")
    val toolCallId: String? = null
) {
    companion object {

        class Builder {
            private var role: ChatMessageRole? = null

            private var content: ChatMessageContent? = null

            private var reasoningContent: String? = null

            private var name: String? = null

            private var functionCall: ChatFunctionCall? = null

            private var toolCalls: List<ChatToolCall?>? = null

            private var toolCallId: String? = null

            fun role(role: ChatMessageRole?): Builder {
                this.role = role
                return this
            }

            fun content(content: String): Builder {
                this.content = ChatMessageContent.TextContent(content)
                return this
            }

            fun multiContent(multiContent: List<ContentPart>): Builder {
                this.content = ChatMessageContent.MultiContent(multiContent)
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
                return ChatMessage(
                    role = role,
                    content = content,
                    reasoningContent = reasoningContent,
                    name = name,
                    functionCall = functionCall,
                    toolCalls = toolCalls,
                    toolCallId = toolCallId,
                )
            }
        }

        fun builder(): Builder {
            return Builder()
        }
    }
}
