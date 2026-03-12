package com.volcengine.ark.runtime.model.responses.event.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class DoubaoAppCallBlockDoneEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_BLOCK_DONE) {
    @SerialName("block")
    private var block: DoubaoAppCallBlock? = null

    fun getBlock(): DoubaoAppCallBlock? {
        return block
    }

    fun setBlock(block: DoubaoAppCallBlock?) {
        this.block = block
    }


    class Builder {
        private val event: DoubaoAppCallBlockDoneEvent = com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallBlockDoneEvent()

        fun block(block: DoubaoAppCallBlock?): Builder {
            event.setBlock(block)
            return this
        }

        fun build(): DoubaoAppCallBlockDoneEvent {
            return event
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallBlockDoneEvent.Builder()
        }
    }
}
