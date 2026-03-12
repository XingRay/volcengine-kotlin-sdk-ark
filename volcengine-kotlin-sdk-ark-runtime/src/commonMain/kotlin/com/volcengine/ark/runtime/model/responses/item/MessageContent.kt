package com.volcengine.ark.runtime.model.responses.item

import com.volcengine.ark.runtime.model.responses.content.InputContentItem
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable(with = MessageContentSerializer::class)
data class MessageContent(
    val stringValue: String? = null,
    val listValue: List<InputContentItem>? = null
)

object MessageContentSerializer : KSerializer<MessageContent> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("MessageContent")

    override fun serialize(encoder: Encoder, value: MessageContent) {
        val jsonEncoder = encoder as? JsonEncoder
            ?: throw SerializationException("MessageContent 只能用于 JSON 格式")

        when {
            value.stringValue != null -> jsonEncoder.encodeString(value.stringValue)
            value.listValue != null -> {
                jsonEncoder.encodeJsonElement(
                    Json.encodeToJsonElement(ListSerializer(InputContentItem.serializer()), value.listValue)
                )
            }
            else -> jsonEncoder.encodeNull()
        }
    }

    override fun deserialize(decoder: Decoder): MessageContent {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("MessageContent 只能用于 JSON 格式")

        val element = jsonDecoder.decodeJsonElement()

        return when {
            element is JsonPrimitive && element.isString -> MessageContent(stringValue = element.content)
            element is JsonArray -> {
                val list = Json.decodeFromJsonElement(ListSerializer(InputContentItem.serializer()), element)
                MessageContent(listValue = list)
            }
            element is JsonNull -> MessageContent()
            else -> throw SerializationException("MessageContent 的 JSON 类型不支持")
        }
    }
}
