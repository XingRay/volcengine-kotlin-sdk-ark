package com.volcengine.ark.runtime.model.responses.event

import com.fasterxml.jackson.annotation.JsonProperty

class ErrorEvent : StreamEvent(ResponsesConstants.EVENT_TYPE_ERROR) {
    @JsonProperty("code")
    var code: String? = null

    @JsonProperty("message")
    var message: String? = null

    @JsonProperty("param")
    var param: String? = null

    @Override
    fun toString(): String? {
        return "ErrorEvent{" +
                "type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", param='" + param + '\'' +
                '}'
    }
}