package com.volcengine.ark.runtime.model.responses.event.mcp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class MCPCallArgumentsDoneEvent : OutputEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_CALL_ARGUMENTS_DONE) {
    @SerialName("item_id")
    var itemId: String? = null

    @SerialName("arguments")
    var arguments: String? = null

}
