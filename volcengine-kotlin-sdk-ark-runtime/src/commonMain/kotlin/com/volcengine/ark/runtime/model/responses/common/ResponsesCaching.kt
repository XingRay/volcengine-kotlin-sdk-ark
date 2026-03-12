package com.volcengine.ark.runtime.model.responses.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponsesCaching(
    @SerialName("type")
    val type: String? = null,

    @SerialName("prefix")
    val prefix: Boolean? = null
)