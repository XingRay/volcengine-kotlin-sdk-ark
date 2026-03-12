package com.volcengine.ark.runtime.model.responses.tool
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import kotlinx.serialization.json.JsonElement

@Serializable
data class ToolFunction(
    @SerialName("type")
    override val type: String? = ResponsesConstants.TOOL_TYPE_FUNCTION,
    @SerialName("name")
    val name: String? = null,
    @SerialName("strict")
    val strict: Boolean? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("parameters")
    val parameters: JsonElement? = null
) : ResponsesTool()