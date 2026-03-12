package com.volcengine.ark.runtime.model.responses.event.mcp

import com.fasterxml.jackson.annotation.JsonProperty

class MCPCallArgumentsDoneEvent : OutputEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_CALL_ARGUMENTS_DONE) {
    @JsonProperty("item_id")
    var itemId: String? = null

    @JsonProperty("arguments")
    var arguments: String? = null

    @Override
    fun toString(): String? {
        return "MCPCallArgumentsDoneEvent{" +
                "itemId='" + itemId + '\'' +
                ", arguments='" + arguments + '\'' +
                ", outputIndex=" + outputIndex +
                ", type='" + type + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                '}'
    }
}
