package com.volcengine.ark.runtime.model.responses.event.response

import com.fasterxml.jackson.annotation.JsonProperty

class ResponseCreatedEvent : StreamEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_CREATED) {
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
        return "ResponseCreatedEvent{" +
                "type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                ", response=" + response +
                '}'
    }
}