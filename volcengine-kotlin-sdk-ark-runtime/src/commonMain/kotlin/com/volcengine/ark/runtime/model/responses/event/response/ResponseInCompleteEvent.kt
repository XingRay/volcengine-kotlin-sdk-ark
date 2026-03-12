package com.volcengine.ark.runtime.model.responses.event.response

import com.fasterxml.jackson.annotation.JsonProperty

class ResponseInCompleteEvent : StreamEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_INCOMPLETE) {
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
        return "ResponseInCompleteEvent{" +
                "type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                ", response=" + response +
                '}'
    }
}