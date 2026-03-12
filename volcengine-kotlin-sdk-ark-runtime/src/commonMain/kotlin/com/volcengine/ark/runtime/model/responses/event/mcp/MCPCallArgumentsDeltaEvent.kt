package com.volcengine.ark.runtime.model.responses.event.mcp

import com.fasterxml.jackson.annotation.JsonProperty

class MCPCallArgumentsDeltaEvent : OutputEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_CALL_ARGUMENTS_DELTA) {
    @JsonProperty("item_id")
    var itemId: String? = null

    @JsonProperty("delta")
    var delta: String? = null

    @Override
    fun toString(): String? {
        return "MCPCallArgumentsDeltaEvent{" +
                "itemId='" + itemId + '\'' +
                ", delta='" + delta + '\'' +
                ", outputIndex=" + outputIndex +
                ", type='" + type + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                '}'
    }
}
