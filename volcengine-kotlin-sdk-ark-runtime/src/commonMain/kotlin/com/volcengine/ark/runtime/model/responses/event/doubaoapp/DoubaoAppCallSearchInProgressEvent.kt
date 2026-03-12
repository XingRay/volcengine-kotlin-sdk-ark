package com.volcengine.ark.runtime.model.responses.event.doubaoapp

import com.fasterxml.jackson.annotation.JsonProperty

class DoubaoAppCallSearchInProgressEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_SEARCH_IN_PROGRESS) {
    @JsonProperty("block_index")
    var blockIndex: Long? = null

    @Override
    fun toString(): String? {
        return "DoubaoAppCallSearchInProgressEvent{" +
                "type='" + getType() + '\'' +
                ", blockIndex=" + blockIndex +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                ", sequenceNumber=" + getSequenceNumber() +
                '}'
    }

    class Builder {
        private val event: DoubaoAppCallSearchInProgressEvent = com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallSearchInProgressEvent()

        fun blockIndex(blockIndex: Long?): Builder {
            event.blockIndex = blockIndex
            return this
        }

        fun build(): DoubaoAppCallSearchInProgressEvent {
            return event
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallSearchInProgressEvent.Builder()
        }
    }
}
