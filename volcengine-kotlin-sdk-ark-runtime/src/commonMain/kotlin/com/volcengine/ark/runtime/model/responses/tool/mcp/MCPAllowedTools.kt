package com.volcengine.ark.runtime.model.responses.tool.mcp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class MCPAllowedTools(
    @SerialName("allowed_tools")
    val allowedTools: List<String?>? = null,

    @SerialName("filter")
    val filter: MCPToolFilter? = null
)
