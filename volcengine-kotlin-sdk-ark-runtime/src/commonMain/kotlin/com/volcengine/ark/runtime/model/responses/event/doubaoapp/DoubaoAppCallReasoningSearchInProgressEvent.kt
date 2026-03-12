package com.volcengine.ark.runtime.model.responses.event.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class DoubaoAppCallReasoningSearchInProgressEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_SEARCH_IN_PROGRESS) {
    @SerialName("block_index")
    var blockIndex: Long? = null


    class Builder {
        private val event: DoubaoAppCallReasoningSearchInProgressEvent = com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallReasoningSearchInProgressEvent()

        fun blockIndex(blockIndex: Long?): Builder {
            event.blockIndex = blockIndex
            return this
        }

        fun build(): DoubaoAppCallReasoningSearchInProgressEvent {
            return event
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallReasoningSearchInProgressEvent.Builder()
        }
    }
}
