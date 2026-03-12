package com.volcengine.ark.runtime.model.responses.tool.mcp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class MCPTool(
    @SerialName("name")
    val name: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("input_schema")
    val inputSchema: JsonElement? = null
)
