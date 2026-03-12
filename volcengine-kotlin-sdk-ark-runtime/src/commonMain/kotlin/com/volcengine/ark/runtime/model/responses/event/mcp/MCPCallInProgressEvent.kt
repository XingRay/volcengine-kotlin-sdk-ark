package com.volcengine.ark.runtime.model.responses.event.mcp

import com.fasterxml.jackson.annotation.JsonProperty

class MCPCallInProgressEvent : OutputEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_CALL_IN_PROGRESS) {
    @JsonProperty("item_id")
    var itemId: String? = null

    @Override
    fun toString(): String? {
        return "MCPCallInProgressEvent{" +
                "itemId='" + itemId + '\'' +
                ", outputIndex=" + outputIndex +
                ", type='" + type + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                '}'
    }
}
