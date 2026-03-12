package com.volcengine.ark.runtime.model.responses.content

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ContentItemType.CONTENT_ITEM_TYPE_INPUT_IMAGE)
data class InputContentItemImage(
    @SerialName("type")
    override val type: String = ContentItemType.CONTENT_ITEM_TYPE_INPUT_IMAGE,
    @SerialName("detail")
    var detail: String? = null,
    @SerialName("image_url")
    var imageUrl: String? = null,
    @SerialName("file_id")
    var fileId: String? = null,
    @SerialName("image_pixel_limit")
    var imagePixelLimit: ImagePixelLimit? = null
) : InputContentItem(type)