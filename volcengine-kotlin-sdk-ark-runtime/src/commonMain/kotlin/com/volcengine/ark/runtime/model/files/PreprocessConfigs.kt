package com.volcengine.ark.runtime.model.files

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PreprocessConfigs(
    @SerialName(value = "video")
    val video: Video? = null
)