package com.volcengine.ark.runtime.model.responses.event
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ErrorEvent : StreamEvent(ResponsesConstants.EVENT_TYPE_ERROR) {
    @SerialName("code")
    var code: String? = null

    @SerialName("message")
    var message: String? = null

    @SerialName("param")
    var param: String? = null

}