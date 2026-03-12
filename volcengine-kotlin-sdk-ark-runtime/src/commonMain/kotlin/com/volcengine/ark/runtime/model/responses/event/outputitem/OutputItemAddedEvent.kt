package com.volcengine.ark.runtime.model.responses.event.outputitem

import com.fasterxml.jackson.annotation.JsonProperty

class OutputItemAddedEvent : OutputEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_ITEM_ADDED) {
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
        return "OutputItemAddedEvent{" +
                "item=" + item +
                ", outputIndex=" + outputIndex +
                ", type='" + type + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                '}'
    }
}
