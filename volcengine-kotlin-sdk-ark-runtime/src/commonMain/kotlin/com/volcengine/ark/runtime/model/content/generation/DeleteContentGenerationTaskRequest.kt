package com.volcengine.ark.runtime.model.content.generation
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class DeleteContentGenerationTaskRequest(
    @SerialName("task_id")
    val taskId: String? = null
)