package com.volcengine.ark.runtime.model.responses.event.mcp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class MCPCallArgumentsDeltaEvent : OutputEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_CALL_ARGUMENTS_DELTA) {
    @SerialName("item_id")
    var itemId: String? = null

    @SerialName("delta")
    var delta: String? = null

}
