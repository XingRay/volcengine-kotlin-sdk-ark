package com.volcengine.ark.runtime.model.content.generation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Content(
    @SerialName("video_url")
    val videoUrl: String? = null,

    @SerialName("last_frame_url")
    val lastFrameUrl: String? = null,

    @SerialName("file_url")
    val fileUrl: String? = null
)