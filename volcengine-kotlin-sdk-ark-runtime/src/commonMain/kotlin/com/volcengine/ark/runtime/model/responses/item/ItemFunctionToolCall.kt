package com.volcengine.ark.runtime.model.responses.item

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ItemFunctionToolCall : BaseItem(ResponsesConstants.ITEM_TYPE_FUNCTION_CALL), InputItem, OutputItem {
    @JsonProperty("arguments")
    var arguments: String? = null

    @JsonProperty("call_id")
    var callId: String? = null

    @JsonProperty("name")
    var name: String? = null

    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("status")
    var status: String? = null

    @Override
    fun toString(): String? {
        return "ItemFunctionToolCall{" +
                "arguments='" + arguments + '\'' +
                ", callId='" + callId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + getType() + '\'' +
                ", id='" + id + '\'' +
                ", status='" + status + '\'' +
                '}'
    }

    class Builder {
        private var arguments: String? = null
        private var callId: String? = null
        private var name: String? = null
        private var id: String? = null
        private var status: String? = null

        fun arguments(arguments: String?): Builder {
            this.arguments = arguments
            return this
        }

        fun callId(callId: String?): Builder {
            this.callId = callId
            return this
        }

        fun name(name: String?): Builder {
            this.name = name
            return this
        }

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun status(status: String?): Builder {
            this.status = status
            return this
        }

        fun build(): ItemFunctionToolCall {
            val itemFunctionToolCall: ItemFunctionToolCall = com.volcengine.ark.runtime.model.responses.item.ItemFunctionToolCall()
            itemFunctionToolCall.arguments = arguments
            itemFunctionToolCall.callId = callId
            itemFunctionToolCall.name = name
            itemFunctionToolCall.id = id
            itemFunctionToolCall.status = status
            return itemFunctionToolCall
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.ItemFunctionToolCall.Builder()
        }
    }
}