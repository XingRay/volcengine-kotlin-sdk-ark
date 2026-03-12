package com.volcengine.ark.runtime.model.responses.event.mcp

import com.fasterxml.jackson.annotation.JsonProperty

class MCPListToolCompletedEvent : OutputEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_LIST_TOOLS_COMPLETED) {
    @JsonProperty("item_id")
    var itemId: String? = null

    @Override
    fun toString(): String? {
        return "MCPListToolCompletedEvent{" +
                "itemId='" + itemId + '\'' +
                ", outputIndex=" + outputIndex +
                ", type='" + type + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                '}'
    }
}
