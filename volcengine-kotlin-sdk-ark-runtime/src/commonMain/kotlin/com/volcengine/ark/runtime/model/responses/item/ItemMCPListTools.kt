package com.volcengine.ark.runtime.model.responses.item

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import com.volcengine.ark.runtime.model.responses.tool.mcp.MCPTool
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.ITEM_TYPE_MCP_LIST_TOOLS)
data class ItemMCPListTools(
    @SerialName("type")
    override val type: String = ResponsesConstants.ITEM_TYPE_MCP_LIST_TOOLS,
    @SerialName("id")
    val id: String? = null,
    @SerialName("server_label")
    val serverLabel: String? = null,
    @SerialName("tools")
    val tools: List<MCPTool?>? = null
) : BaseItem(type) {
    constructor() : this(ResponsesConstants.ITEM_TYPE_MCP_LIST_TOOLS)
}
