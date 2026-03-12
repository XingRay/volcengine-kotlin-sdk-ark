package com.volcengine.ark.runtime.model.responses.item

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.ITEM_TYPE_FUNCTION_CALL_OUTPUT)
data class ItemFunctionToolCallOutput(
    @SerialName("type")
    override val type: String = ResponsesConstants.ITEM_TYPE_FUNCTION_CALL_OUTPUT,
    @SerialName("output")
    val output: String? = null,
    @SerialName("call_id")
    val callId: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("status")
    val status: String? = null
) : BaseItem(type) {
    constructor() : this(ResponsesConstants.ITEM_TYPE_FUNCTION_CALL_OUTPUT)
}