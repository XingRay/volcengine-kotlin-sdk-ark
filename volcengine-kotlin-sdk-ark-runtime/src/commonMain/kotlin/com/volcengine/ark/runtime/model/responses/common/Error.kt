package com.volcengine.ark.runtime.model.responses.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Error(
    @SerialName("code")
    val code: String? = null,

    @SerialName("message")
    val message: String? = null
)