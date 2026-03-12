package com.volcengine.ark.runtime.model.responses.tool.mcp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class MCPRequireApproval(
    @SerialName("mode")
    val mode: String? = null,

    @SerialName("filter")
    val filter: MCPToolApprovalFilter? = null
)
