package com.volcengine.ark.runtime.model.responses.item

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.ITEM_TYPE_ITEM_REFERENCE)
data class ItemReference(
    @SerialName("type")
    override val type: String = ResponsesConstants.ITEM_TYPE_ITEM_REFERENCE,
    @SerialName("id")
    val id: String? = null
) : BaseItem(type) {
    constructor() : this(ResponsesConstants.ITEM_TYPE_ITEM_REFERENCE)
}