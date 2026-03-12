package com.volcengine.ark.runtime.model.responses.content

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.CONTENT_ITEM_TYPE_INPUT_IMAGE)
data class InputContentItemImage(
    @SerialName("type")
    override val type: String = ResponsesConstants.CONTENT_ITEM_TYPE_INPUT_IMAGE,
    @SerialName("detail")
    val detail: String? = null,
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("file_id")
    val fileId: String? = null,
    @SerialName("image_pixel_limit")
    val imagePixelLimit: ImagePixelLimit? = null
) : InputContentItem(type) {
    constructor() : this(ResponsesConstants.CONTENT_ITEM_TYPE_INPUT_IMAGE)
}