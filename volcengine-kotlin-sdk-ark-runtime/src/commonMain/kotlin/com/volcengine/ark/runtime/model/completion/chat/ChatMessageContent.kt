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
@Serializable(with = ContentPartSerializer::class)
sealed class ContentPart {
    /**
     * 文本类型
     */
    @Serializable
    data class TextPart(
        val text: String
    ) : ContentPart()

    /**
     * 图片 URL 类型
     */
    @Serializable
    data class ImageUrlPart(
        val imageUrl: ImageUrl,
    ) : ContentPart()

    /**
     * 视频 URL 类型
     */
    @Serializable
    data class VideoUrlPart(
        val videoUrl: VideoUrl,
        val fps: Double? = null
    ) : ContentPart()
}

/**
 * ContentPart 自定义序列化器
 */
object ContentPartSerializer : KSerializer<ContentPart> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("ContentPart", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: ContentPart) {
        require(encoder is JsonEncoder)
        val jsonObject = when (value) {
            is ContentPart.TextPart -> buildJsonObject {
                put("type", "text")
                put("text", value.text)
            }

            is ContentPart.ImageUrlPart -> buildJsonObject {
                put("type", "image_url")
                put("image_url", encoder.json.encodeToJsonElement(ImageUrl.serializer(), value.imageUrl))
            }

            is ContentPart.VideoUrlPart -> buildJsonObject {
                put("type", "video_url")
                put("video_url", encoder.json.encodeToJsonElement(VideoUrl.serializer(), value.videoUrl))
                value.fps?.let { put("fps", it) }
            }
        }
        encoder.encodeJsonElement(jsonObject)
    }

    override fun deserialize(decoder: Decoder): ContentPart {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        require(element is JsonObject)

        val type = element["type"]?.jsonPrimitive?.content
            ?: throw IllegalArgumentException("Missing 'type' field in ContentPart")

        return when (type) {
            "text" -> {
                val text = element["text"]?.jsonPrimitive?.content
                    ?: throw IllegalArgumentException("Missing 'text' field in TextPart")
                ContentPart.TextPart(text)
            }

            "image_url" -> {
                val imageUrl = element["image_url"]?.let {
                    decoder.json.decodeFromJsonElement(ImageUrl.serializer(), it)
                } ?: throw IllegalArgumentException("Missing 'image_url' field in ImageUrlPart")
                ContentPart.ImageUrlPart(imageUrl)
            }

            "video_url" -> {
                val videoUrl = element["video_url"]?.let {
                    decoder.json.decodeFromJsonElement(VideoUrl.serializer(), it)
                } ?: throw IllegalArgumentException("Missing 'video_url' field in VideoUrlPart")
                val fps = element["fps"]?.jsonPrimitive?.doubleOrNull
                ContentPart.VideoUrlPart(videoUrl, fps)
            }

            else -> throw IllegalArgumentException("Unknown ContentPart type: $type")
        }
    }
}

/**
 * messages.content.image_url.detail string
 * 取值范围：low、high、xhigh。
 * 理解图片的精细度、不同模型默认取值及对应的具体像素区间，参见控制图片理解的精细度。
 */
@Serializable
enum class ImageUrlDetail(val value: String) {

    @SerialName("low")
    LOW("low"),

    @SerialName("high")
    HIGH("high"),

    @SerialName("xhigh")
    XHIGH("xhigh"),

}

/**
 * 图片 URL 数据模型
 */
@Serializable
data class ImageUrl(
    @SerialName("url")
    val url: String,

    @SerialName("detail")
    val detail: ImageUrlDetail? = null,
)

/**
 * 视频 URL 数据模型
 */
@Serializable
data class VideoUrl(
    @SerialName("url")
    val url: String,

    /**
     * 抽帧频率，详见视频理解。
     * 取值范围：[0.2, 5]
     * 取值越高，对视频中画面变化越敏感。
     * 取值越低，对视频中画面变化越迟钝，但 token 花费少，速度更快。
     */
    @SerialName("fps")
    val fps: Float?,

)
