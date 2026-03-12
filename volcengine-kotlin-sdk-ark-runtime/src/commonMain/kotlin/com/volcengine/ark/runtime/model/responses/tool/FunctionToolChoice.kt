package com.volcengine.ark.runtime.model.responses.tool
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class FunctionToolChoice(
    @SerialName("type")
    val type: String? = null,
    @SerialName("name")
    val name: String? = null
)