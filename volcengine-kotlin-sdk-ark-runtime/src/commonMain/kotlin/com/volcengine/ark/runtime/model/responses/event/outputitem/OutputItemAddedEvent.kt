package com.volcengine.ark.runtime.model.responses.event.outputitem
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class OutputItemAddedEvent : OutputEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_ITEM_ADDED) {
    @SerialName("item")
    protected var item: BaseItem? = null

    fun getItem(): BaseItem? {
        return item
    }

    fun setItem(item: BaseItem?) {
        this.item = item
    }


}
