package com.volcengine.ark.runtime.model.completion.chat
import kotlinx.serialization.Serializable


@Serializable
data class ChatFunctionCall(
    /**
     * The name of the function being called
     */
    val name: String? = null,

    /**
     * The arguments of the call produced by the model, represented as a JsonNode for easy manipulation.
     */
    val arguments: String? = null
)
