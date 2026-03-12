package com.volcengine.ark.runtime.model.completion.chat
import kotlinx.serialization.Serializable


@Serializable
data class ChatToolCall(
    /**
     * The ID of the tool call
     */
    val id: String? = null,

    /**
     * The type of the tool. Currently, only function is supported
     */
    val type: String? = null,

    /**
     * The function that the model called
     */
    val function: ChatFunctionCall? = null,

    /**
     * The index of the tool call in the list of tool calls
     */
    val index: Int? = null
)
