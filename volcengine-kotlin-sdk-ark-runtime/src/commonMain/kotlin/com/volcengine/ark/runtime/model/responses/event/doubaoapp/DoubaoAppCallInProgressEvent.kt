package com.volcengine.ark.runtime.model.responses.event.doubaoapp

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import com.volcengine.ark.runtime.model.responses.event.StreamEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_IN_PROGRESS)
data class DoubaoAppCallInProgressEvent(
    @SerialName("type")
    override val type: String = ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_IN_PROGRESS,
    @SerialName("sequence_number")
    override val sequenceNumber: Long? = null,
    @SerialName("output_index")
    val outputIndex: Long? = null,
    @SerialName("item_id")
    val itemId: String? = null,
    @SerialName("feature")
    val feature: String? = null
) : StreamEvent(type) {
    constructor() : this(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_IN_PROGRESS)
}
