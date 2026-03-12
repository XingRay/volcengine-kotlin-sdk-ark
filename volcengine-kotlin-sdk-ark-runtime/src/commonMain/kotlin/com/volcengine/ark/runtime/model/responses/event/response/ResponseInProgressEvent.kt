package com.volcengine.ark.runtime.model.responses.event.response

import com.fasterxml.jackson.annotation.JsonProperty

class ResponseInProgressEvent : StreamEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_IN_PROGRESS) {
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
        return "ResponseInProgressEvent{" +
                "response=" + response +
                ", type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                '}'
    }
}
