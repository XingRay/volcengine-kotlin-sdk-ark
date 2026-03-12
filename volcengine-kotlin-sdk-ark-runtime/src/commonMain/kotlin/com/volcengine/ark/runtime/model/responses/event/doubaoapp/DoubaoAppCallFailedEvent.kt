package com.volcengine.ark.runtime.model.responses.event.doubaoapp

import com.fasterxml.jackson.annotation.JsonProperty

class DoubaoAppCallFailedEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_FAILED) {
    @JsonProperty("error_message")
    var errorMessage: String? = null

    @Override
    fun toString(): String? {
        return "DoubaoAppCallFailedEvent{" +
                "type='" + getType() + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                ", sequenceNumber=" + getSequenceNumber() +
                '}'
    }

    class Builder {
        private val event: DoubaoAppCallFailedEvent = com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallFailedEvent()

        fun errorMessage(errorMessage: String?): Builder {
            event.errorMessage = errorMessage
            return this
        }

        fun build(): DoubaoAppCallFailedEvent {
            return event
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallFailedEvent.Builder()
        }
    }
}
