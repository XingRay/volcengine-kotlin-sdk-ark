package com.volcengine.ark.runtime.model.responses.event.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class DoubaoAppCallReasoningTextDeltaEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_TEXT_DELTA) {
    @SerialName("block_index")
    var blockIndex: Long? = null

    @SerialName("delta")
    var delta: String? = null


    class Builder {
        private val event: DoubaoAppCallReasoningTextDeltaEvent = com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallReasoningTextDeltaEvent()

        fun blockIndex(blockIndex: Long?): Builder {
            event.blockIndex = blockIndex
            return this
        }

        fun delta(delta: String?): Builder {
            event.delta = delta
            return this
        }

        fun build(): DoubaoAppCallReasoningTextDeltaEvent {
            return event
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallReasoningTextDeltaEvent.Builder()
        }
    }
}
