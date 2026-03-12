package com.volcengine.ark.runtime.model.responses.event.reasoningsummary

import com.fasterxml.jackson.annotation.JsonProperty

class ReasoningSummaryTextDoneEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_REASONING_SUMMARY_TEXT_DONE) {
    @JsonProperty("summary_index")
    var summaryIndex: Long? = null

    @JsonProperty("text")
    var text: String? = null

    @Override
    fun toString(): String? {
        return "ReasoningSummaryTextDoneEvent{" +
                "summaryIndex=" + summaryIndex +
                ", text='" + text + '\'' +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                ", type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                '}'
    }
}