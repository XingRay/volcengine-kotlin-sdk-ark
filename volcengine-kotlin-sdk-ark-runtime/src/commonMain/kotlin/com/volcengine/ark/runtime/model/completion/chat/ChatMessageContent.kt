package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

/**
 * 密封类：统一表示 ChatMessage 的 content 字段的多种类型
 * - 字符串类型：简单文本消息
 * - 多内容类型：包含文本、图片、视频等多种内容的数组
 */
@Serializable(with = ChatMessageContentSerializer::class)
sealed class ChatMessageContent {

    /**
     * 文本内容类型
     */
    @Serializable
    data class TextContent(val value: String) : ChatMessageContent()

    /**
     * 多内容类型（包含文本、图片、视频等）
     */
    @Serializable
    data class MultiContent(val items: List<ContentPart>) : ChatMessageContent()
}

/**
 * 自定义序列化器：根据 JSON 结构动态选择反序列化策略
 */
object ChatMessageContentSerializer : KSerializer<ChatMessageContent> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("ChatMessageContent", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: ChatMessageContent) {
        require(encoder is JsonEncoder)
        val element = when (value) {
            is ChatMessageContent.TextContent -> JsonPrimitive(value.value)
            is ChatMessageContent.MultiContent -> {
                encoder.json.encodeToJsonElement(
                    kotlinx.serialization.builtins.ListSerializer(ContentPart.serializer()),
                    value.items
                )
            }
        }
        encoder.encodeJsonElement(element)
    }

    override fun deserialize(decoder: Decoder): ChatMessageContent {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()

        return when {
            // 字符串类型
            element is JsonPrimitive && element.isString -> {
                ChatMessageContent.TextContent(element.content)
            }
            // 数组类型
            element is JsonArray -> {
                val items = decoder.json.decodeFromJsonElement(
                    kotlinx.serialization.builtins.ListSerializer(ContentPart.serializer()),
                    element
                )
                ChatMessageContent.MultiContent(items)
            }
            else -> throw IllegalArgumentException("不支持的 content 类型: ${element::class.simpleName}")
        }
    }
}

/**
 * 内容部分：密封类表示多内容数组中的单个项
 */
@Serializable
sealed class ContentPart {
    abstract val type: String

    /**
     * 文本类型
     */
    @Serializable
    @SerialName("text")
    data class TextPart(
        @SerialName("type") override val type: String = "text",
        @SerialName("text") val text: String
    ) : ContentPart()

    /**
     * 图片 URL 类型
     */
    @Serializable
    @SerialName("image_url")
    data class ImageUrlPart(
        @SerialName("type") override val type: String = "image_url",
        @SerialName("image_url") val imageUrl: ImageUrl
    ) : ContentPart()

    /**
     * 视频 URL 类型
     */
    @Serializable
    @SerialName("video_url")
    data class VideoUrlPart(
        @SerialName("type") override val type: String = "video_url",
        @SerialName("video_url") val videoUrl: VideoUrl,
        @SerialName("fps") val fps: Double? = null
    ) : ContentPart()
}

/**
 * 图片 URL 数据模型
 */
@Serializable
data class ImageUrl(
    @SerialName("url")
    val url: String,

    @SerialName("detail")
    val detail: String? = null
)

/**
 * 视频 URL 数据模型
 */
@Serializable
data class VideoUrl(
    @SerialName("url")
    val url: String
)
