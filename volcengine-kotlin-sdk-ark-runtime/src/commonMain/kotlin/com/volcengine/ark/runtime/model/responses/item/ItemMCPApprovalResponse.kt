package com.volcengine.ark.runtime.model.responses.item

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.ITEM_TYPE_MCP_APPROVAL_RESPONSE)
data class ItemMCPApprovalResponse(
    @SerialName("type")
    override val type: String = ResponsesConstants.ITEM_TYPE_MCP_APPROVAL_RESPONSE,
    @SerialName("approve")
    val approve: Boolean? = null,
    @SerialName("approval_request_id")
    val approvalRequestId: String? = null
) : BaseItem(type) {
    constructor() : this(ResponsesConstants.ITEM_TYPE_MCP_APPROVAL_RESPONSE)
}