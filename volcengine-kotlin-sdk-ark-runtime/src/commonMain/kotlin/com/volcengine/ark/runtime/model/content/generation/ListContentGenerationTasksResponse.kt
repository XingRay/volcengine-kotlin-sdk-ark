package com.volcengine.ark.runtime.model.content.generation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ListContentGenerationTasksResponse(
    @SerialName("total")
    val total: Int = 0,
    @SerialName("items")
    val items: List<Item>? = null
)

