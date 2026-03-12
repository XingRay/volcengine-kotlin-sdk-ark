package com.volcengine.ark.runtime.model.responses.content

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ImagePixelLimit(
    @SerialName("max_pixels")
    val maxPixels: Long? = null,

    @SerialName("min_pixels")
    val minPixels: Long? = null
)