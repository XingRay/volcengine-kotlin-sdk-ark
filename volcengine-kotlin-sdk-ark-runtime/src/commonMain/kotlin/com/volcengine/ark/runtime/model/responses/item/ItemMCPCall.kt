package com.volcengine.ark.runtime.model.responses.item
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ItemMCPCall : BaseItem(ResponsesConstants.ITEM_TYPE_MCP_CALL), InputItem, OutputItem {
    @SerialName("id")
    var id: String? = null

    @SerialName("server_label")
    var serverLabel: String? = null

    @SerialName("approval_request_id")
    var approvalRequestId: String? = null

    @SerialName("arguments")
    var arguments: String? = null

    @SerialName("error")
    var error: String? = null

    @SerialName("name")
    var name: String? = null

    @SerialName("output")
    var output: String? = null


    class Builder private constructor() {
        private var output: String? = null
        private var name: String? = null
        private var error: String? = null
        private var arguments: String? = null
        private var approvalRequestId: String? = null
        private var serverLabel: String? = null
        private var id: String? = null

        fun output(output: String?): Builder {
            this.output = output
            return this
        }

        fun name(name: String?): Builder {
            this.name = name
            return this
        }

        fun error(error: String?): Builder {
            this.error = error
            return this
        }

        fun arguments(arguments: String?): Builder {
            this.arguments = arguments
            return this
        }

        fun approvalRequestId(approvalRequestId: String?): Builder {
            this.approvalRequestId = approvalRequestId
            return this
        }

        fun serverLabel(serverLabel: String?): Builder {
            this.serverLabel = serverLabel
            return this
        }

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun build(): ItemMCPCall {
            val itemMCPCall: ItemMCPCall = com.volcengine.ark.runtime.model.responses.item.ItemMCPCall()
            itemMCPCall.output = output
            itemMCPCall.name = name
            itemMCPCall.error = error
            itemMCPCall.arguments = arguments
            itemMCPCall.approvalRequestId = approvalRequestId
            itemMCPCall.serverLabel = serverLabel
            itemMCPCall.id = id
            return itemMCPCall
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.ItemMCPCall.Builder()
        }
    }
}
