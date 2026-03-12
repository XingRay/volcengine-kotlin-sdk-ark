package com.volcengine.ark.runtime.model.responses.event.functioncall

import com.fasterxml.jackson.annotation.JsonProperty

class FunctionCallArgumentsDeltaEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_FUNCTION_CALL_ARGUMENTS_DELTA) {
    @JsonProperty("delta")
    var delta: String? = null

    @Override
    fun toString(): String? {
        return "FunctionCallArgumentsDeltaEvent{" +
                "delta='" + delta + '\'' +
                ", itemId='" + itemId + '\'' +
                ", outputIndex=" + outputIndex +
                ", type='" + type + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                '}'
    }
}