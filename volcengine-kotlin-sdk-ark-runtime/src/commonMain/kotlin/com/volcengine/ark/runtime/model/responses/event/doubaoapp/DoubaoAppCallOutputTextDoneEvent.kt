package com.volcengine.ark.runtime.model.responses.event.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class DoubaoAppCallOutputTextDoneEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_OUTPUT_TEXT_DONE) {
    @SerialName("block_index")
    var blockIndex: Long? = null

    @SerialName("text")
    var text: String? = null


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
