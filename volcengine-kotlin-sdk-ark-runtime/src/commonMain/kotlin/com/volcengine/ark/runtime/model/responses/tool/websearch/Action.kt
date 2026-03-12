package com.volcengine.ark.runtime.model.responses.tool.websearch
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Action(
    @SerialName("query")
    val query: String? = null,

    @SerialName("type")
    val type: String? = null
)