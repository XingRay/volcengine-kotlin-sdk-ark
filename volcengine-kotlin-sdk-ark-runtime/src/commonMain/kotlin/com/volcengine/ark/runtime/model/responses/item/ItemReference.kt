package com.volcengine.ark.runtime.model.responses.item

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ItemReference : BaseItem(ResponsesConstants.ITEM_TYPE_ITEM_REFERENCE), InputItem {
    @JsonProperty("id")
    var id: String? = null

    @Override
    fun toString(): String? {
        return "ItemReference{" +
                "id='" + id + '\'' +
                ", type='" + getType() + '\'' +
                '}'
    }

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