package com.volcengine.ark.runtime.model.responses.event.doubaoapp

import com.fasterxml.jackson.annotation.JsonProperty

class DoubaoAppCallOutputTextDeltaEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_OUTPUT_TEXT_DELTA) {
    @JsonProperty("block_index")
    var blockIndex: Long? = null

    @JsonProperty("delta")
    var delta: String? = null

    @Override
    fun toString(): String? {
        return "DoubaoAppCallOutputTextDeltaEvent{" +
                "type='" + getType() + '\'' +
                ", blockIndex=" + blockIndex +
                ", delta='" + delta + '\'' +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                ", sequenceNumber=" + getSequenceNumber() +
                '}'
    }

    class Builder {
        private val event: DoubaoAppCallOutputTextDeltaEvent = com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallOutputTextDeltaEvent()

        fun blockIndex(blockIndex: Long?): Builder {
            event.blockIndex = blockIndex
            return this
        }

        fun delta(delta: String?): Builder {
            event.delta = delta
            return this
        }

        fun build(): DoubaoAppCallOutputTextDeltaEvent {
            return event
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallOutputTextDeltaEvent.Builder()
        }
    }
}
