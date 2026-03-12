package com.volcengine.ark.runtime.model.files

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Video(
    @SerialName(value = "fps")
    val fps: Double? = null
)