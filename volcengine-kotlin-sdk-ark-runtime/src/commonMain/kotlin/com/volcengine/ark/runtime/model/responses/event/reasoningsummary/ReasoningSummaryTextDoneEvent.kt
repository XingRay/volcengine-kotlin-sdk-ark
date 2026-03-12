package com.volcengine.ark.runtime.model.responses.event.reasoningsummary
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ReasoningSummaryTextDoneEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_REASONING_SUMMARY_TEXT_DONE) {
    @SerialName("summary_index")
    var summaryIndex: Long? = null

    @SerialName("text")
    var text: String? = null

}