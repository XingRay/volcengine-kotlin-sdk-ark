package com.volcengine.ark.runtime.model.responses.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject


@Serializable
data class ResponsesTextFormat(
    @SerialName("type")
    val type: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("schema")
    val schema: JsonObject? = null
)