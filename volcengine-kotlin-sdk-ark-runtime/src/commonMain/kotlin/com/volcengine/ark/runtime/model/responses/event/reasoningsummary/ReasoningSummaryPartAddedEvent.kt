package com.volcengine.ark.runtime.model.responses.event.reasoningsummary

import com.fasterxml.jackson.annotation.JsonProperty

class ReasoningSummaryPartAddedEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_REASONING_SUMMARY_PART_ADDED) {
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
        return "ReasoningSummaryPartAddedEvent{" +
                "type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                ", summaryIndex=" + summaryIndex +
                ", part=" + part +
                '}'
    }
}