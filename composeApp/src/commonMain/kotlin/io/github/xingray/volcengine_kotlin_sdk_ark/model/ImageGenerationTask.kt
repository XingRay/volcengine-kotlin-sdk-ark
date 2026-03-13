package io.github.xingray.volcengine_kotlin_sdk_ark.model

import kotlinx.serialization.Serializable

@Serializable
data class ImageGenerationTask(
    val taskId: String,
    val status: TaskStatus,
    val imageUrl: String? = null,
    val errorMessage: String? = null
)

enum class TaskStatus {
    PENDING,
    PROCESSING,
    COMPLETED,
    FAILED
}
