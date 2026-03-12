package com.volcengine.ark.runtime.model.responses.event

import com.fasterxml.jackson.annotation.JsonProperty

class OutputEvent(type: String?) : StreamEvent(type) {
    @JsonProperty("output_index")
    var outputIndex: Long? = null

    @Override
    fun toString(): String? {
        return "OutputEvent{" +
                "type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                ", outputIndex=" + outputIndex +
                '}'
    }
}