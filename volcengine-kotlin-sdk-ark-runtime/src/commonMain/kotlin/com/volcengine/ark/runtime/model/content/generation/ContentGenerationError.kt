package com.volcengine.ark.runtime.model.content.generation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContentGenerationError(
    @SerialName("message")
    val message: String? = null,

    @SerialName("code")
    val code: String? = null
)