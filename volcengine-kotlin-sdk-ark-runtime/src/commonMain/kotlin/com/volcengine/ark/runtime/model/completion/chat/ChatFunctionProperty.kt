package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatFunctionProperty(
    @SerialName("name")
    val name: String? = null,

    @SerialName("type")
    val type: String? = null,

    @SerialName("required")
    val required: Boolean? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("items")
    val items: ChatFunctionProperty? = null,

    @SerialName("enum")
    val enumValues: List<String>? = null
)
