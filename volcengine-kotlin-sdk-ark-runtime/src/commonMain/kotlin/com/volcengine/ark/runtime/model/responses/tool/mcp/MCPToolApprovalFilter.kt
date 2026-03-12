package com.volcengine.ark.runtime.model.responses.tool.mcp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class MCPToolApprovalFilter(
    @SerialName("always")
    val always: MCPToolFilter? = null,

    @SerialName("never")
    val never: MCPToolFilter? = null
)
