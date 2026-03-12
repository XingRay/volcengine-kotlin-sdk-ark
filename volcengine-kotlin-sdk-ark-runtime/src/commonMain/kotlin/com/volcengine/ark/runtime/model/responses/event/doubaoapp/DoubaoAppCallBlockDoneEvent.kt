package com.volcengine.ark.runtime.model.responses.event.doubaoapp

import com.fasterxml.jackson.annotation.JsonProperty

class DoubaoAppCallBlockDoneEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_BLOCK_DONE) {
    @JsonProperty("block")
    private var block: DoubaoAppCallBlock? = null

    fun getBlock(): DoubaoAppCallBlock? {
        return block
    }

    fun setBlock(block: DoubaoAppCallBlock?) {
        this.block = block
    }

    @Override
    fun toString(): String? {
        return "DoubaoAppCallBlockDoneEvent{" +
                "type='" + getType() + '\'' +
                ", block=" + block +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                ", sequenceNumber=" + getSequenceNumber() +
                '}'
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
