package com.volcengine.ark.runtime.model.responses.item
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ItemMCPApprovalResponse : BaseItem(ResponsesConstants.ITEM_TYPE_MCP_APPROVAL_RESPONSE), InputItem {
    @SerialName("approve")
    var approve: Boolean? = null

    @SerialName("approval_request_id")
    var approvalRequestId: String? = null


    class Builder private constructor() {
        private var approve: Boolean? = null
        private var approvalRequestId: String? = null

        fun approve(approve: Boolean?): Builder {
            this.approve = approve
            return this
        }

        fun approvalRequestId(approvalRequestId: String?): Builder {
            this.approvalRequestId = approvalRequestId
            return this
        }

        fun build(): ItemMCPApprovalResponse {
            val itemMCPApprovalResponse: ItemMCPApprovalResponse = com.volcengine.ark.runtime.model.responses.item.ItemMCPApprovalResponse()
            itemMCPApprovalResponse.approve = approve
            itemMCPApprovalResponse.approvalRequestId = approvalRequestId
            return itemMCPApprovalResponse
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.ItemMCPApprovalResponse.Builder()
        }
    }
}
