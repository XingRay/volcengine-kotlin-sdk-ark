package com.volcengine.ark.runtime.model.images.generation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContentGenerationTool(
    @SerialName("type")
    var type: String? = null
)