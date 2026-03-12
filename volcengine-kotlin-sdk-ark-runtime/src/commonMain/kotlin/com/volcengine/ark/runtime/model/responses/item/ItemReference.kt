package com.volcengine.ark.runtime.model.responses.item
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ItemReference : BaseItem(ResponsesConstants.ITEM_TYPE_ITEM_REFERENCE), InputItem {
    @SerialName("id")
    var id: String? = null


    class Builder {
        private var id: String? = null

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun build(): ItemReference {
            val itemReference: ItemReference = com.volcengine.ark.runtime.model.responses.item.ItemReference()
            itemReference.id = id
            return itemReference
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.ItemReference.Builder()
        }
    }
}