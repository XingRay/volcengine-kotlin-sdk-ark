package com.volcengine.ark.runtime.model.responses.event.response

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import com.volcengine.ark.runtime.model.responses.event.StreamEvent
import com.volcengine.ark.runtime.model.responses.response.ResponseObject
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
@SerialName(ResponsesConstants.EVENT_TYPE_RESPONSE_INCOMPLETE)
data class ResponseInCompleteEvent(
    @SerialName("type")
    override val type: String = ResponsesConstants.EVENT_TYPE_RESPONSE_INCOMPLETE,

    @SerialName("sequence_number")
    override val sequenceNumber: Long? = null,

    @SerialName("response")
    val response: ResponseObject? = null
) : StreamEvent(type) {
    constructor() : this(ResponsesConstants.EVENT_TYPE_RESPONSE_INCOMPLETE)
}