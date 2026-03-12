package com.volcengine.ark.runtime.model.responses.event.doubaoapp

import com.fasterxml.jackson.annotation.JsonProperty

class DoubaoAppCallReasoningSearchSearchingEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_SEARCH_SEARCHING) {
    @JsonProperty("block_index")
    var blockIndex: Long? = null

    @JsonProperty("searching_state")
    var searchingState: String? = null

    @Override
    fun toString(): String? {
        return "DoubaoAppCallReasoningSearchSearchingEvent{" +
                "type='" + getType() + '\'' +
                ", blockIndex=" + blockIndex +
                ", searchingState='" + searchingState + '\'' +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                ", sequenceNumber=" + getSequenceNumber() +
                '}'
    }

    class Builder {
        private val event: DoubaoAppCallReasoningSearchSearchingEvent = com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallReasoningSearchSearchingEvent()

        fun blockIndex(blockIndex: Long?): Builder {
            event.blockIndex = blockIndex
            return this
        }

        fun searchingState(searchingState: String?): Builder {
            event.searchingState = searchingState
            return this
        }

        fun build(): DoubaoAppCallReasoningSearchSearchingEvent {
            return event
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallReasoningSearchSearchingEvent.Builder()
        }
    }
}
