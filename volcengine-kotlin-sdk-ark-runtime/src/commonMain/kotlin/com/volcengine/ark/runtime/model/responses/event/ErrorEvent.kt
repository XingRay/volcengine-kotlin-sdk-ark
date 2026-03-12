package com.volcengine.ark.runtime.model.responses.event

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.EVENT_TYPE_ERROR)
data class ErrorEvent(
    @SerialName("type")
    override val type: String = ResponsesConstants.EVENT_TYPE_ERROR,
    @SerialName("sequence_number")
    override val sequenceNumber: Long? = null,
    @SerialName("code")
    val code: String? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("param")
    val param: String? = null
) : StreamEvent(type) {
    constructor() : this(ResponsesConstants.EVENT_TYPE_ERROR)
}