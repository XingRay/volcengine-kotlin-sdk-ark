package com.volcengine.ark.runtime.model.responses.event.doubaoapp

import com.fasterxml.jackson.annotation.JsonProperty

class DoubaoAppCallOutputTextDoneEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_OUTPUT_TEXT_DONE) {
    @JsonProperty("block_index")
    var blockIndex: Long? = null

    @JsonProperty("text")
    var text: String? = null

    @Override
    fun toString(): String? {
        return "DoubaoAppCallOutputTextDoneEvent{" +
                "type='" + getType() + '\'' +
                ", blockIndex=" + blockIndex +
                ", text='" + text + '\'' +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                ", sequenceNumber=" + getSequenceNumber() +
                '}'
    }

    class Builder {
        private val event: DoubaoAppCallOutputTextDoneEvent = com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallOutputTextDoneEvent()

        fun blockIndex(blockIndex: Long?): Builder {
            event.blockIndex = blockIndex
            return this
        }

        fun text(text: String?): Builder {
            event.text = text
            return this
        }

        fun build(): DoubaoAppCallOutputTextDoneEvent {
            return event
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallOutputTextDoneEvent.Builder()
        }
    }
}
