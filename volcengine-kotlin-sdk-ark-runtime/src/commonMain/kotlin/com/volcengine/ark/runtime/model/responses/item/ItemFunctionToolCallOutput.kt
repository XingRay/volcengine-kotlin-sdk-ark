package com.volcengine.ark.runtime.model.responses.item

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ItemFunctionToolCallOutput : BaseItem(ResponsesConstants.ITEM_TYPE_FUNCTION_CALL_OUTPUT), InputItem {
    @JsonProperty("output")
    var output: String? = null

    @JsonProperty("call_id")
    var callId: String? = null

    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("status")
    var status: String? = null

    @Override
    fun toString(): String? {
        return "ItemFunctionToolCallOutput{" +
                "output='" + output + '\'' +
                ", callId='" + callId + '\'' +
                ", type='" + getType() + '\'' +
                ", id='" + id + '\'' +
                ", status='" + status + '\'' +
                '}'
    }

    class Builder {
        private var output: String? = null
        private var callId: String? = null
        private var id: String? = null
        private var status: String? = null

        fun output(output: String?): Builder {
            this.output = output
            return this
        }

        fun callId(callId: String?): Builder {
            this.callId = callId
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

        fun build(): ItemFunctionToolCallOutput {
            val itemFunctionToolCallOutput: ItemFunctionToolCallOutput = com.volcengine.ark.runtime.model.responses.item.ItemFunctionToolCallOutput()
            itemFunctionToolCallOutput.output = output
            itemFunctionToolCallOutput.callId = callId
            itemFunctionToolCallOutput.id = id
            itemFunctionToolCallOutput.status = status
            return itemFunctionToolCallOutput
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.ItemFunctionToolCallOutput.Builder()
        }
    }
}