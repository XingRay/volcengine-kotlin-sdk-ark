package com.volcengine.ark.runtime.model.responses.event.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class DoubaoAppCallBlockAddedEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_BLOCK_ADDED) {
    @SerialName("block")
    private var block: DoubaoAppCallBlock? = null

    fun getBlock(): DoubaoAppCallBlock? {
        return block
    }

    fun setBlock(block: DoubaoAppCallBlock?) {
        this.block = block
    }


    class Builder {
        private val event: DoubaoAppCallBlockAddedEvent = com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallBlockAddedEvent()

        fun block(block: DoubaoAppCallBlock?): Builder {
            event.setBlock(block)
            return this
        }

        fun build(): DoubaoAppCallBlockAddedEvent {
            return event
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallBlockAddedEvent.Builder()
        }
    }
}
