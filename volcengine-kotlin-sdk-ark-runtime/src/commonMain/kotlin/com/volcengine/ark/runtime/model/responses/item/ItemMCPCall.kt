package com.volcengine.ark.runtime.model.responses.item

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.ITEM_TYPE_MCP_CALL)
data class ItemMCPCall(
    @SerialName("type")
    override val type: String = ResponsesConstants.ITEM_TYPE_MCP_CALL,
    @SerialName("id")
    val id: String? = null,
    @SerialName("server_label")
    val serverLabel: String? = null,
    @SerialName("approval_request_id")
    val approvalRequestId: String? = null,
    @SerialName("arguments")
    val arguments: String? = null,
    @SerialName("error")
    val error: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("output")
    val output: String? = null
) : BaseItem(type) {
    constructor() : this(ResponsesConstants.ITEM_TYPE_MCP_CALL)
}
