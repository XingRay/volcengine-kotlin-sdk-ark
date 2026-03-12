package com.volcengine.ark.runtime.model.responses.tool
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import com.volcengine.ark.runtime.model.responses.tool.mcp.MCPAllowedTools
import com.volcengine.ark.runtime.model.responses.tool.mcp.MCPRequireApproval

@Serializable
data class ToolMCP(
    @SerialName("type")
    override val type: String? = ResponsesConstants.TOOL_TYPE_MCP,
    @SerialName("server_label")
    val serverLabel: String? = null,
    @SerialName("server_url")
    val serverUrl: String? = null,
    @SerialName("allowed_tools")
    val allowedTools: MCPAllowedTools? = null,
    @SerialName("headers")
    val headers: Map<String?, String?>? = null,
    @SerialName("require_approval")
    val requireApproval: MCPRequireApproval? = null,
    @SerialName("server_description")
    val serverDescription: String? = null
) : ResponsesTool()
