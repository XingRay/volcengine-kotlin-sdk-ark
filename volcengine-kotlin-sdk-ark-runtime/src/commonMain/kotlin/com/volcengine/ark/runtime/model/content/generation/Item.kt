package com.volcengine.ark.runtime.model.content.generation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    @SerialName("id")
    val id: String? = null,

    @SerialName("model")
    val model: String? = null,

    @SerialName("status")
    val status: String? = null,

    @SerialName("error")
    val error: ContentGenerationError? = null,

    @SerialName("content")
    val content: Content? = null,

    @SerialName("usage")
    val usage: Usage? = null,

    @SerialName("subdivisionlevel")
    val subdivisionLevel: String? = null,

    @SerialName("fileformat")
    val fileFormat: String? = null,

    @SerialName("frames")
    val frames: Long? = null,

    @SerialName("framespersecond")
    val framesPerSecond: Long? = null,

    @SerialName("created_at")
    val createdAt: Long? = null,

    @SerialName("updated_at")
    val updatedAt: Long? = null,

    @SerialName("seed")
    val seed: Long? = null,

    @SerialName("revised_prompt")
    val revisedPrompt: String? = null,

    @SerialName("service_tier")
    val serviceTier: String? = null,

    @SerialName("execution_expires_after")
    val executionExpiresAfter: Long? = null,

    @SerialName("generate_audio")
    val generateAudio: Boolean? = null
)