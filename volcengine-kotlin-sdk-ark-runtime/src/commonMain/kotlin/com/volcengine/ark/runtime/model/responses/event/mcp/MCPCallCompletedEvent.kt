package com.volcengine.ark.runtime.model.responses.event.mcp

import com.fasterxml.jackson.annotation.JsonProperty

class MCPCallCompletedEvent : OutputEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_CALL_COMPLETED) {
    @JsonProperty("item_id")
    var itemId: String? = null

    @Override
    fun toString(): String? {
        return "MCPCallCompletedEvent{" +
                "itemId='" + itemId + '\'' +
                ", outputIndex=" + outputIndex +
                ", type='" + type + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                '}'
    }
}
