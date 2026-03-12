package com.volcengine.ark.runtime.model.responses.item

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ItemWebSearchCall : BaseItem(ResponsesConstants.ITEM_TYPE_WEB_SEARCH_CALL), OutputItem {
    @JsonProperty("action")
    private var action: Action? = null

    @JsonProperty("status")
    var status: String? = null

    @JsonProperty("id")
    var id: String? = null

    fun getAction(): Action? {
        return action
    }

    fun setAction(action: Action?) {
        this.action = action
    }

    @Override
    fun toString(): String? {
        return "ItemWebSearchCall{" +
                "type='" + getType() + '\'' +
                ", action=" + action +
                ", status='" + status + '\'' +
                ", id='" + id + '\'' +
                '}'
    }

    class Builder {
        private var action: Action? = null
        private var status: String? = null
        private var id: String? = null

        fun action(action: Action?): Builder {
            this.action = action
            return this
        }

        fun status(status: String?): Builder {
            this.status = status
            return this
        }

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun build(): ItemWebSearchCall {
            val itemWebSearchCall: ItemWebSearchCall = com.volcengine.ark.runtime.model.responses.item.ItemWebSearchCall()
            itemWebSearchCall.setAction(action)
            itemWebSearchCall.status = status
            itemWebSearchCall.id = id
            return itemWebSearchCall
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.ItemWebSearchCall.Builder()
        }
    }
}