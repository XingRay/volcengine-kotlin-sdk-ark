package com.volcengine.ark.runtime.model.responses.event.reasoningsummary
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ReasoningSummaryTextDeltaEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_REASONING_SUMMARY_TEXT_DELTA) {
    @SerialName("summary_index")
    var summaryIndex: Long? = null

    @SerialName("delta")
    var delta: String? = null

}