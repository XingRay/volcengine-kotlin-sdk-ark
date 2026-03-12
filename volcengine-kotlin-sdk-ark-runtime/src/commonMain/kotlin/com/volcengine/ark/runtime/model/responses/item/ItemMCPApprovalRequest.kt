package com.volcengine.ark.runtime.model.responses.item
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ItemMCPApprovalRequest : BaseItem(ResponsesConstants.ITEM_TYPE_MCP_APPROVAL_REQUEST), InputItem, OutputItem {
    @SerialName("id")
    var id: String? = null

    @SerialName("name")
    var name: String? = null

    @SerialName("arguments")
    var arguments: String? = null

    @SerialName("server_label")
    var serverLabel: String? = null



    class Builder private constructor() {
        private var id: String? = null
        private var name: String? = null
        private var arguments: String? = null
        private var serverLabel: String? = null

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun name(name: String?): Builder {
            this.name = name
            return this
        }

        fun arguments(arguments: String?): Builder {
            this.arguments = arguments
            return this
        }

        fun serverLabel(serverLabel: String?): Builder {
            this.serverLabel = serverLabel
            return this
        }

        fun build(): ItemMCPApprovalRequest {
            val itemMCPApprovalRequest: ItemMCPApprovalRequest = com.volcengine.ark.runtime.model.responses.item.ItemMCPApprovalRequest()
            itemMCPApprovalRequest.id = id
            itemMCPApprovalRequest.name = name
            itemMCPApprovalRequest.arguments = arguments
            itemMCPApprovalRequest.serverLabel = serverLabel
            return itemMCPApprovalRequest
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.ItemMCPApprovalRequest.Builder()
        }
    }
}
