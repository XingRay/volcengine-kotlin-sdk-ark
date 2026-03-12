package com.volcengine.ark.runtime.model.responses.content

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ContentItemType.CONTENT_ITEM_TYPE_INPUT_TEXT)
data class InputContentItemText(
    @SerialName("type")
    override val type: String = ContentItemType.CONTENT_ITEM_TYPE_INPUT_TEXT,
    @SerialName("text")
    var text: String? = null
) : InputContentItem(type)