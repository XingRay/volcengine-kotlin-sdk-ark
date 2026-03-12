package com.volcengine.ark.runtime.model.responses.event.reasoningsummary

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import com.volcengine.ark.runtime.model.responses.event.StreamEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.EVENT_TYPE_RESPONSE_REASONING_SUMMARY_TEXT_DELTA)
data class ReasoningSummaryTextDeltaEvent(
    @SerialName("type")
    override val type: String = ResponsesConstants.EVENT_TYPE_RESPONSE_REASONING_SUMMARY_TEXT_DELTA,
    @SerialName("sequence_number")
    override val sequenceNumber: Long? = null,
    @SerialName("output_index")
    val outputIndex: Long? = null,
    @SerialName("item_id")
    val itemId: String? = null,
    @SerialName("summary_index")
    val summaryIndex: Long? = null,
    @SerialName("delta")
    val delta: String? = null
) : StreamEvent(type) {
    constructor() : this(ResponsesConstants.EVENT_TYPE_RESPONSE_REASONING_SUMMARY_TEXT_DELTA)
}