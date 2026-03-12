package com.volcengine.ark.runtime.model.responses.event.outputtext

import com.fasterxml.jackson.annotation.JsonProperty

class OutputTextDoneEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_TEXT_DONE) {
    @JsonProperty("content_index")
    var contentIndex: Long? = null

    @JsonProperty("text")
    var text: String? = null

    @Override
    fun toString(): String? {
        return "OutputTextDoneEvent{" +
                "contentIndex=" + contentIndex +
                ", text='" + text + '\'' +
                ", itemId='" + itemId + '\'' +
                ", outputIndex=" + outputIndex +
                ", type='" + type + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                '}'
    }
}