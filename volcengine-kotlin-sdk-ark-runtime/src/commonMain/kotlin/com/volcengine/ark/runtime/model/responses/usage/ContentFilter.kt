package com.volcengine.ark.runtime.model.responses.usage
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class ContentFilter(
    @SerialName("type")
    val type: String? = null,

    @SerialName("details")
    val details: String? = null
)