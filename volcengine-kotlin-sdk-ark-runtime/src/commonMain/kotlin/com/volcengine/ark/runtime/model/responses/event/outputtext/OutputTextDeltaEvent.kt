package com.volcengine.ark.runtime.model.responses.event.outputtext

import com.fasterxml.jackson.annotation.JsonProperty

class OutputTextDeltaEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_TEXT_DELTA) {
    @JsonProperty("content_index")
    var contentIndex: Long? = null

    @JsonProperty("delta")
    var delta: String? = null

    @Override
    fun toString(): String? {
        return "OutputTextDeltaEvent{" +
                "contentIndex=" + contentIndex +
                ", delta='" + delta + '\'' +
                ", itemId='" + itemId + '\'' +
                ", outputIndex=" + outputIndex +
                ", type='" + type + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                '}'
    }
}