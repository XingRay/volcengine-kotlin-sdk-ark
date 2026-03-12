package com.volcengine.ark.runtime.model.content.generation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DraftTask(
    @SerialName("id")
    val id: String? = null,
)