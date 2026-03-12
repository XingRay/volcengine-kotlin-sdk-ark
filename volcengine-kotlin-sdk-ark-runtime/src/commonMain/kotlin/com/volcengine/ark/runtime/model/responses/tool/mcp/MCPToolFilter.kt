package com.volcengine.ark.runtime.model.responses.tool.mcp

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class MCPToolFilter(
    @SerialName("tool_names")
    val toolNames: List<String?>? = null
)
