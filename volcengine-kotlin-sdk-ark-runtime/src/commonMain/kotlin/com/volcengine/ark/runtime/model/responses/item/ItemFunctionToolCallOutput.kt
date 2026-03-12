package com.volcengine.ark.runtime.model.responses.item
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ItemFunctionToolCallOutput : BaseItem(ResponsesConstants.ITEM_TYPE_FUNCTION_CALL_OUTPUT), InputItem {
    @SerialName("output")
    var output: String? = null

    @SerialName("call_id")
    var callId: String? = null

    @SerialName("id")
    var id: String? = null

    @SerialName("status")
    var status: String? = null


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