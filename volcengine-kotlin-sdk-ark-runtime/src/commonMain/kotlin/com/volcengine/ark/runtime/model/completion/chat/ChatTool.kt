package com.volcengine.ark.runtime.model.completion.chat
import kotlinx.serialization.Serializable


@Serializable
data class ChatTool(
    /**
     * The type of the tool. Currently, only function is supported.
     */
    val type: String? = null,


    val function: ChatFunction? = null
)