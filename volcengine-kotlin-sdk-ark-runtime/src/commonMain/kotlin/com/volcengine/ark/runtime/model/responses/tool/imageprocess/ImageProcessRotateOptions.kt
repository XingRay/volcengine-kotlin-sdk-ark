package com.volcengine.ark.runtime.model.responses.tool.imageprocess
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class ImageProcessRotateOptions(
    @SerialName("type")
    val type: String? = null
)