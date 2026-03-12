package com.volcengine.ark.runtime.model.responses.event.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class DoubaoAppCallCompletedEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_COMPLETED) {
    @SerialName("feature")
    var feature: String? = null

    @SerialName("blocks")
    private var blocks: List<DoubaoAppCallBlock?>? = null

    fun getBlocks(): List<DoubaoAppCallBlock?>? {
        return blocks
    }

    fun setBlocks(blocks: List<DoubaoAppCallBlock?>?) {
        this.blocks = blocks
    }


    class Builder {
        private val event: DoubaoAppCallCompletedEvent = com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallCompletedEvent()

        fun feature(feature: String?): Builder {
            event.feature = feature
            return this
        }

        fun blocks(blocks: List<DoubaoAppCallBlock?>?): Builder {
            event.setBlocks(blocks)
            return this
        }

        fun build(): DoubaoAppCallCompletedEvent {
            return event
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallCompletedEvent.Builder()
        }
    }
}
