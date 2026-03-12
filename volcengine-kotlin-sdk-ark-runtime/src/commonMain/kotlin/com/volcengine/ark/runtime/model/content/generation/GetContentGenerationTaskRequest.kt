package com.volcengine.ark.runtime.model.content.generation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetContentGenerationTaskRequest(
    @SerialName("task_id")
    val taskId: String? = null
)