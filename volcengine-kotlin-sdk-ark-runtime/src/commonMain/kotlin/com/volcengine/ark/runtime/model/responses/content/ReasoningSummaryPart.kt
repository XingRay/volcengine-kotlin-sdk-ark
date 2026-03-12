package com.volcengine.ark.runtime.model.responses.content

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ReasoningSummaryPart(
    @SerialName("type")
    val type: String? = null,

    @SerialName("text")
    val text: String? = null
)