package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.SerialName
import  kotlinx.serialization.Serializable

@Serializable
sealed class ContentItem {
    // 图片类型
    @SerialName("image_url")
    @Serializable
    data class ImageUrlItem(
        @SerialName("image_url")
        val imageUrl: ImageUrl,

        @SerialName("type")
        val type: String = "image_url",

    ) : ContentItem()

    // 文本类型
    @SerialName("text")
    @Serializable
    data class TextItem(
        @SerialName("text")
        val text: String,

        @SerialName("type")
        val type: String = "text"
    ) : ContentItem()

    // 新增：视频类型（包含fps字段）
    @SerialName("video_url")
    @Serializable
    data class VideoUrlItem(
        @SerialName("video_url")
        val videoUrl: VideoUrl,

        @SerialName("fps")
        val fps: String? = null, // fps为可选字段

        @SerialName("type")
        val type: String = "video_url"
    ) : ContentItem()
}
