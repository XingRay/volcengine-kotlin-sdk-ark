package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ChatCompletionRequestResponseFormat(
    val type: String? = null,
    val jsonSchema: ResponseFormatJSONSchemaJSONSchemaParam? = null,

    @kotlin.Deprecated("")
    val schema: JsonElement? = null,
)
