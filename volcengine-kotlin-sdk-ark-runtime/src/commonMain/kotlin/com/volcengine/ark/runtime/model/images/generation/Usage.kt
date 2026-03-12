package com.volcengine.ark.runtime.model.images.generation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Usage(
    @SerialName("generated_images")
    val generatedImages: Int? = null,

    @SerialName("output_tokens")
    val outputTokens: Int? = null,

    @SerialName("total_tokens")
    val totalTokens: Int? = null,

    @SerialName("tool_usage")
    val toolUsage: ToolUsage? = null,
)