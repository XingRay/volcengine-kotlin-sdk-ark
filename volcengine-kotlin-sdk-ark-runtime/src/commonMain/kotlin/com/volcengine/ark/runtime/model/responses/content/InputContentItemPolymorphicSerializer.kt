package com.volcengine.ark.runtime.model.responses.content

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object InputContentItemPolymorphicSerializer : JsonContentPolymorphicSerializer<InputContentItem>(InputContentItem::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<InputContentItem> {
        // 从 JSON 中读取 "type" 字段，匹配对应子类
        val type = element.jsonObject["type"]?.jsonPrimitive?.content ?: throw SerializationException(
            "Missing 'type' field for InputContentItem deserialization"
        )
        return when (type) {
            ContentItemType.CONTENT_ITEM_TYPE_INPUT_TEXT -> InputContentItemText.serializer()
            ContentItemType.CONTENT_ITEM_TYPE_INPUT_IMAGE -> InputContentItemImage.serializer()
            ContentItemType.CONTENT_ITEM_TYPE_INPUT_VIDEO -> InputContentItemVideo.serializer()
            ContentItemType.CONTENT_ITEM_TYPE_INPUT_FILE -> InputContentItemFile.serializer()
            else -> throw SerializationException("Unsupported InputContentItem type: $type")
        }
    }
}
