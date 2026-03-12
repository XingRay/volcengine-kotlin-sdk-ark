package com.volcengine.ark.runtime.model.responses.event.reasoningsummary
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ReasoningSummaryPartDoneEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_REASONING_SUMMARY_PART_DONE) {
    @SerialName("summary_index")
    var summaryIndex: Long? = null

    @SerialName("part")
    private var part: ReasoningSummaryPart? = null

    fun getPart(): ReasoningSummaryPart? {
        return part
    }

    fun setPart(part: ReasoningSummaryPart?) {
        this.part = part
    }

}