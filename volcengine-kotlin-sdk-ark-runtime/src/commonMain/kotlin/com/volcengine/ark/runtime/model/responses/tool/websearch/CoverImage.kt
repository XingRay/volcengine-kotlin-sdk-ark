package com.volcengine.ark.runtime.model.responses.tool.websearch
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class CoverImage(
    @SerialName("url")
    val url: String? = null,

    @SerialName("width")
    val width: Long? = null,

    @SerialName("height")
    val height: Long? = null
)