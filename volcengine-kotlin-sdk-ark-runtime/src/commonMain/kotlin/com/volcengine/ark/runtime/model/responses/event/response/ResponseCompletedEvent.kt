package com.volcengine.ark.runtime.model.responses.event.response

import com.fasterxml.jackson.annotation.JsonProperty

class ResponseCompletedEvent : StreamEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_COMPLETED) {
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
        return "ResponseCompletedEvent{" +
                "type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                ", response=" + response +
                '}'
    }
}