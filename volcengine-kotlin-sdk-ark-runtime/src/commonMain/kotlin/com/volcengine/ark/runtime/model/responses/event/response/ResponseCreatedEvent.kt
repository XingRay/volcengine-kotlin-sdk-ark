package com.volcengine.ark.runtime.model.responses.event.response
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ResponseCreatedEvent : StreamEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_CREATED) {
    @SerialName("response")
    private var response: ResponseObject? = null

    fun getResponse(): ResponseObject? {
        return response
    }

    fun setResponse(response: ResponseObject?) {
        this.response = response
    }

}