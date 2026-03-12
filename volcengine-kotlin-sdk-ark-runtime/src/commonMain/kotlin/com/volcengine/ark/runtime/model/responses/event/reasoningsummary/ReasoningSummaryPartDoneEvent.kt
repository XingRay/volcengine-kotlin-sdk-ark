package com.volcengine.ark.runtime.model.responses.event.reasoningsummary

import com.fasterxml.jackson.annotation.JsonProperty

class ReasoningSummaryPartDoneEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_REASONING_SUMMARY_PART_DONE) {
    @JsonProperty("summary_index")
    var summaryIndex: Long? = null

    @JsonProperty("part")
    private var part: ReasoningSummaryPart? = null

    fun getPart(): ReasoningSummaryPart? {
        return part
    }

    fun setPart(part: ReasoningSummaryPart?) {
        this.part = part
    }

    @Override
    fun toString(): String? {
        return "ReasoningSummaryPartDoneEvent{" +
                "summaryIndex=" + summaryIndex +
                ", part=" + part +
                ", itemId='" + itemId + '\'' +
                ", outputIndex=" + getOutputIndex() +
                ", type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                '}'
    }
}