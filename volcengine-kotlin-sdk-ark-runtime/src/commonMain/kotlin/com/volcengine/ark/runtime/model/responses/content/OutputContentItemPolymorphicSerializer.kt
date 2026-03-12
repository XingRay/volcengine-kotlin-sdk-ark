package com.volcengine.ark.runtime.model.responses.content

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object OutputContentItemPolymorphicSerializer : JsonContentPolymorphicSerializer<OutputContentItem>(OutputContentItem::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<OutputContentItem> {
        // 读取 "type" 字段，匹配对应子类
        val type = element.jsonObject["type"]?.jsonPrimitive?.content ?: throw SerializationException(
            "Missing 'type' field for OutputContentItem deserialization"
        )
        return when (type) {
            ResponsesConstants.CONTENT_ITEM_TYPE_OUTPUT_TEXT -> OutputContentItemText.serializer()
            else -> throw SerializationException("Unsupported OutputContentItem type: $type")
        }
    }
}