package com.volcengine.ark.runtime.model.responses.event.reasoningsummary

import com.fasterxml.jackson.annotation.JsonProperty

class ReasoningSummaryTextDeltaEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_REASONING_SUMMARY_TEXT_DELTA) {
    @JsonProperty("summary_index")
    var summaryIndex: Long? = null

    @JsonProperty("delta")
    var delta: String? = null

    @Override
    fun toString(): String? {
        return "ReasoningSummaryTextDeltaEvent{" +
                "summaryIndex=" + summaryIndex +
                ", delta='" + delta + '\'' +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                ", type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                '}'
    }
}