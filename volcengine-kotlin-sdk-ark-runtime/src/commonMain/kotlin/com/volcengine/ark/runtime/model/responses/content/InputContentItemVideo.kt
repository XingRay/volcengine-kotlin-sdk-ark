package com.volcengine.ark.runtime.model.responses.content

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ContentItemType.CONTENT_ITEM_TYPE_INPUT_VIDEO)
data class InputContentItemVideo(
    @SerialName("type")
    override val type: String = ContentItemType.CONTENT_ITEM_TYPE_INPUT_VIDEO,
    @SerialName("video_url")
    var videoUrl: String? = null,
    @SerialName("file_id")
    var fileId: String? = null,
    @SerialName("fps")
    var fps: Float? = null
) : InputContentItem(type)