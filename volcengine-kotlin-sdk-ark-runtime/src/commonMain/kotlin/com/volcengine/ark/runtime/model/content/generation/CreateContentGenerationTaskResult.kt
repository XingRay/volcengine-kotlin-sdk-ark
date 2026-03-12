package com.volcengine.ark.runtime.model.content.generation
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class CreateContentGenerationTaskResult(
    @SerialName("id")
    val id: String? = null
)