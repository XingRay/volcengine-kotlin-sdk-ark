package com.volcengine.ark.runtime.model.responses.event.functioncall

import com.fasterxml.jackson.annotation.JsonProperty

class FunctionCallArgumentsDoneEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_FUNCTION_CALL_ARGUMENTS_DONE) {
    @JsonProperty("arguments")
    var arguments: String? = null

    @Override
    fun toString(): String? {
        return "FunctionCallArgumentsDoneEvent{" +
                "arguments='" + arguments + '\'' +
                ", itemId='" + itemId + '\'' +
                ", outputIndex=" + outputIndex +
                ", type='" + type + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                '}'
    }
}