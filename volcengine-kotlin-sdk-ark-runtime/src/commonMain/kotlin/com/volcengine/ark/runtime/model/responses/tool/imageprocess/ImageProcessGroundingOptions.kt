package com.volcengine.ark.runtime.model.responses.tool.imageprocess
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class ImageProcessGroundingOptions(
    @SerialName("type")
    val type: String? = null
)