package com.volcengine.ark.runtime.model.responses.content

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.CONTENT_ITEM_TYPE_INPUT_VIDEO)
data class InputContentItemVideo(
    @SerialName("type")
    override val type: String = ResponsesConstants.CONTENT_ITEM_TYPE_INPUT_VIDEO,
    @SerialName("video_url")
    val videoUrl: String? = null,
    @SerialName("file_id")
    val fileId: String? = null,
    @SerialName("fps")
    val fps: Float? = null
) : InputContentItem(type) {
    constructor() : this(ResponsesConstants.CONTENT_ITEM_TYPE_INPUT_VIDEO)
}