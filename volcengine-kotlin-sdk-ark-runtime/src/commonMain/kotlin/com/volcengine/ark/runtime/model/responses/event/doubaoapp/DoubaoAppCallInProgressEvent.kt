package com.volcengine.ark.runtime.model.responses.event.doubaoapp

import com.fasterxml.jackson.annotation.JsonProperty

class DoubaoAppCallInProgressEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_IN_PROGRESS) {
    @JsonProperty("feature")
    var feature: String? = null

    @Override
    fun toString(): String? {
        return "DoubaoAppCallInProgressEvent{" +
                "type='" + getType() + '\'' +
                ", feature='feature'" +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                ", sequenceNumber=" + getSequenceNumber() +
                '}'
    }

    class Builder {
        private val event: DoubaoAppCallInProgressEvent = com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallInProgressEvent()

        fun feature(feature: String?): Builder {
            event.feature = feature
            return this
        }

        fun build(): DoubaoAppCallInProgressEvent {
            return event
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallInProgressEvent.Builder()
        }
    }
}
