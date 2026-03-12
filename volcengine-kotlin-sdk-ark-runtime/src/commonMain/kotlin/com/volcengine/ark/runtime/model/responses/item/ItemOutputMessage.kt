package com.volcengine.ark.runtime.model.responses.item

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import com.volcengine.ark.runtime.model.responses.content.OutputContentItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.ITEM_TYPE_MESSAGE)
data class ItemOutputMessage(
    @SerialName("type")
    override val type: String = ResponsesConstants.ITEM_TYPE_MESSAGE,
    @SerialName("role")
    val role: String? = null,
    @SerialName("content")
    val content: List<OutputContentItem?>? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("partial")
    val partial: Boolean? = null
) : BaseItem(type) {
    constructor() : this(ResponsesConstants.ITEM_TYPE_MESSAGE)
}