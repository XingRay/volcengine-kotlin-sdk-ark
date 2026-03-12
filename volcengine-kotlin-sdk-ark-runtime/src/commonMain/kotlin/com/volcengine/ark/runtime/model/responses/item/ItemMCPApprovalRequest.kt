package com.volcengine.ark.runtime.model.responses.item

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.ITEM_TYPE_MCP_APPROVAL_REQUEST)
data class ItemMCPApprovalRequest(
    @SerialName("type")
    override val type: String = ResponsesConstants.ITEM_TYPE_MCP_APPROVAL_REQUEST,
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("arguments")
    val arguments: String? = null,
    @SerialName("server_label")
    val serverLabel: String? = null
) : BaseItem(type) {
    constructor() : this(ResponsesConstants.ITEM_TYPE_MCP_APPROVAL_REQUEST)
}
