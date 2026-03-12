package com.volcengine.ark.runtime.model.responses.item

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.ITEM_TYPE_MESSAGE)
data class ItemEasyMessage(
    @SerialName("type")
    override val type: String = ResponsesConstants.ITEM_TYPE_MESSAGE,
    @SerialName("role")
    val role: String? = null,
    @SerialName("content")
    val content: MessageContent? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("partial")
    val partial: Boolean? = null
) : BaseItem(type) {
    constructor() : this(ResponsesConstants.ITEM_TYPE_MESSAGE)
}