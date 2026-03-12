package com.volcengine.ark.runtime.model.responses.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponsesText(
    @SerialName("format")
    val format: ResponsesTextFormat? = null
)