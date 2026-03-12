package com.volcengine.ark.runtime.model.responses.item

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import com.volcengine.ark.runtime.model.responses.tool.websearch.Action
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.ITEM_TYPE_WEB_SEARCH_CALL)
data class ItemWebSearchCall(
    @SerialName("type")
    override val type: String = ResponsesConstants.ITEM_TYPE_WEB_SEARCH_CALL,
    @SerialName("action")
    val action: Action? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("id")
    val id: String? = null
) : BaseItem(type) {
    constructor() : this(ResponsesConstants.ITEM_TYPE_WEB_SEARCH_CALL)
}