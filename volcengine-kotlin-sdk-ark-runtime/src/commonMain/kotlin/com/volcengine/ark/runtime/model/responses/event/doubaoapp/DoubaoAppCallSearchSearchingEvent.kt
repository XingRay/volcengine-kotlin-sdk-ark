package com.volcengine.ark.runtime.model.responses.event.doubaoapp

import com.fasterxml.jackson.annotation.JsonProperty

class DoubaoAppCallSearchSearchingEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_SEARCH_SEARCHING) {
    @JsonProperty("block_index")
    var blockIndex: Long? = null

    @JsonProperty("searching_state")
    var searchingState: String? = null

    @Override
    fun toString(): String? {
        return "DoubaoAppCallSearchSearchingEvent{" +
                "type='" + getType() + '\'' +
                ", blockIndex=" + blockIndex +
                ", searchingState='" + searchingState + '\'' +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                ", sequenceNumber=" + getSequenceNumber() +
                '}'
    }

    class Builder {
        private val event: DoubaoAppCallSearchSearchingEvent = com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallSearchSearchingEvent()

        fun blockIndex(blockIndex: Long?): Builder {
            event.blockIndex = blockIndex
            return this
        }

        fun searchingState(searchingState: String?): Builder {
            event.searchingState = searchingState
            return this
        }

        fun build(): DoubaoAppCallSearchSearchingEvent {
            return event
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallSearchSearchingEvent.Builder()
        }
    }
}
