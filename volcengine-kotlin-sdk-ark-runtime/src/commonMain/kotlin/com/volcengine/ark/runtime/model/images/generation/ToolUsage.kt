package com.volcengine.ark.runtime.model.images.generation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ToolUsage(
    @SerialName("web_search")
    val webSearch: Int? = null
)