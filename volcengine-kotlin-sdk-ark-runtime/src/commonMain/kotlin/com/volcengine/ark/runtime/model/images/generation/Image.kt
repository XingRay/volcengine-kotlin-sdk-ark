package com.volcengine.ark.runtime.model.images.generation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("url")
    val url: String? = null,

    @SerialName("b64_json")
    val b64Json: String? = null,

    @SerialName("size")
    val size: String? = null
)