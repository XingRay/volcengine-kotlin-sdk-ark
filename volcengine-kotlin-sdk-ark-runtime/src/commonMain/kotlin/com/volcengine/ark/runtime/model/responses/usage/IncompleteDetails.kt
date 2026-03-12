package com.volcengine.ark.runtime.model.responses.usage
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class IncompleteDetails(
    @SerialName("reason")
    val reason: String? = null,

    @SerialName("content_filter")
    val contentFilter: ContentFilter? = null
)