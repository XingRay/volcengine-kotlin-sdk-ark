package com.volcengine.ark.runtime.model.responses.event.outputitem

import com.fasterxml.jackson.annotation.JsonProperty

class OutputItemDoneEvent : OutputEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_ITEM_DONE) {
    @JsonProperty("item")
    protected var item: BaseItem? = null

    fun getItem(): BaseItem? {
        return item
    }

    fun setItem(item: BaseItem?) {
        this.item = item
    }


    @Override
    fun toString(): String? {
        return "OutputItemDoneEvent{" +
                "item=" + item +
                ", outputIndex=" + outputIndex +
                ", type='" + type + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                '}'
    }
}