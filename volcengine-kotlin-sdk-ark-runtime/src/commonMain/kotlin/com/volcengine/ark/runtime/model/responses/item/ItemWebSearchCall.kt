package com.volcengine.ark.runtime.model.responses.item
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ItemWebSearchCall : BaseItem(ResponsesConstants.ITEM_TYPE_WEB_SEARCH_CALL), OutputItem {
    @SerialName("action")
    private var action: Action? = null

    @SerialName("status")
    var status: String? = null

    @SerialName("id")
    var id: String? = null

    fun getAction(): Action? {
        return action
    }

    fun setAction(action: Action?) {
        this.action = action
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