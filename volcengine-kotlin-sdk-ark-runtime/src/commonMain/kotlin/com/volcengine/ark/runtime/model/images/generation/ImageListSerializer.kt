package com.volcengine.ark.runtime.model.images.generation

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

object ImageListSerializer : KSerializer<List<String>> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("ImageListSerializer", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: List<String>) {
        val jsonEncoder = encoder as JsonEncoder
        // 若只有一个元素，可序列化为单个字符串（可选，和原逻辑保持一致）
        val jsonElement = if (value.size == 1) {
            JsonPrimitive(value[0])
        } else {
            JsonArray(value.map { JsonPrimitive(it) })
        }
        jsonEncoder.encodeJsonElement(jsonElement)
    }

    override fun deserialize(decoder: Decoder): List<String> {
        val input = decoder as? JsonDecoder ?: throw SerializationException("仅支持 JSON 格式反序列化")
        val node = input.decodeJsonElement()

        return when (node) {
            // 单个字符串 → 单元素 List
            is JsonPrimitive if node.isString -> listOf(node.content)
            // 数组 → 元素列表
            is JsonArray -> node.mapNotNull { it.jsonPrimitive.contentOrNull }
            // 其他类型（null/对象）→ 空 List
            else -> emptyList()
        }
    }
}
