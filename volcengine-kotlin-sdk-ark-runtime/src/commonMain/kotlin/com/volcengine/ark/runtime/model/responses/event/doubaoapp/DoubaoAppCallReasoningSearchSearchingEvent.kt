package com.volcengine.ark.runtime.model.responses.event.doubaoapp

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import com.volcengine.ark.runtime.model.responses.event.StreamEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_SEARCH_SEARCHING)
data class DoubaoAppCallReasoningSearchSearchingEvent(
    @SerialName("type")
    override val type: String = ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_SEARCH_SEARCHING,
    @SerialName("sequence_number")
    override val sequenceNumber: Long? = null,
    @SerialName("output_index")
    val outputIndex: Long? = null,
    @SerialName("item_id")
    val itemId: String? = null,
    @SerialName("block_index")
    val blockIndex: Long? = null,
    @SerialName("searching_state")
    val searchingState: String? = null
) : StreamEvent(type) {
    constructor() : this(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_SEARCH_SEARCHING)
}
