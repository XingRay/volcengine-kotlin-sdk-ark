package com.volcengine.ark.runtime.model.responses.event.mcp

import com.fasterxml.jackson.annotation.JsonProperty

class MCPListToolInProgressEvent : OutputEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_LIST_TOOLS_IN_PROGRESS) {
    @JsonProperty("item_id")
    var itemId: String? = null

    @Override
    fun toString(): String? {
        return "MCPListToolInProgressEvent{" +
                "itemId='" + itemId + '\'' +
                ", outputIndex=" + outputIndex +
                ", type='" + type + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                '}'
    }
}
