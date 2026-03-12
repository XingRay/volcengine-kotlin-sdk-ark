package com.volcengine.ark.runtime.model.responses.content

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.CONTENT_ITEM_TYPE_INPUT_TEXT)
data class InputContentItemText(
    @SerialName("type")
    override val type: String = ResponsesConstants.CONTENT_ITEM_TYPE_INPUT_TEXT,
    @SerialName("text")
    val text: String? = null
) : InputContentItem(type) {
    constructor() : this(ResponsesConstants.CONTENT_ITEM_TYPE_INPUT_TEXT)
}