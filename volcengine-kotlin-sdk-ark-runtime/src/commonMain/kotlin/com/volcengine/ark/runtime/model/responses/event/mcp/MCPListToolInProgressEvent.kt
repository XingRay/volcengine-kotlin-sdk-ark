package com.volcengine.ark.runtime.model.responses.event.mcp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class MCPListToolInProgressEvent : OutputEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_LIST_TOOLS_IN_PROGRESS) {
    @SerialName("item_id")
    var itemId: String? = null

}
