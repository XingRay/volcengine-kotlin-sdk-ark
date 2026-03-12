package com.volcengine.ark.runtime.model.responses.tool.imageprocess
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class ImageProcessAction(
    @SerialName("type")
    val type: String? = null,

    @SerialName("result_image_url")
    val resultImageUrl: String? = null
)