package com.volcengine.ark.runtime.model.responses.event.response

import com.fasterxml.jackson.annotation.JsonProperty

class ResponseFailedEvent : StreamEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_FAILED) {
    @JsonProperty("response")
    private var response: ResponseObject? = null

    fun getResponse(): ResponseObject? {
        return response
    }

    fun setResponse(response: ResponseObject?) {
        this.response = response
    }

    @Override
    fun toString(): String? {
        return "ResponseFailedEvent{" +
                "type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                ", response=" + response +
                '}'
    }
}