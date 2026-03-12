package com.volcengine.ark.runtime.model.responses.tool.imageprocess
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class ImageProcessError(
    @SerialName("message")
    val message: String? = null
)