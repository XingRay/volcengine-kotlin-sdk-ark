package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject


@Serializable
data class ResponseFormatJSONSchemaJSONSchemaParam(
    val name: String,
    val description: String? = null,
    val schema: JsonObject? = null,
    val strict: Boolean = false
)