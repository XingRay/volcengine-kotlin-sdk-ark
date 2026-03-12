package com.volcengine.ark.runtime.model.completion.chat

/**
 * 扩展函数：从旧的 ChatCompletionContentPart 转换为新的 ContentPart
 */
fun ChatCompletionContentPart.toContentPart(): ContentPart {
    return when (type) {
        "text" -> ContentPart.TextPart(
            text = text ?: content ?: ""
        )
        "image_url" -> ContentPart.ImageUrlPart(
            imageUrl = ImageUrl(
                url = imageUrl?.url ?: "",
                detail = imageUrl?.detail
            )
        )
        "video_url" -> ContentPart.VideoUrlPart(
            videoUrl = VideoUrl(
                url = videoUrl?.url ?: ""
            ),
            fps = videoUrl?.fps
        )
        else -> throw IllegalArgumentException("不支持的 content part 类型: $type")
    }
}

/**
 * 扩展函数：从新的 ContentPart 转换为旧的 ChatCompletionContentPart
 */
fun ContentPart.toChatCompletionContentPart(): ChatCompletionContentPart {
    val part = ChatCompletionContentPart()
    when (this) {
        is ContentPart.TextPart -> {
            part.type = "text"
            part.text = this.text
        }
        is ContentPart.ImageUrlPart -> {
            part.type = "image_url"
            part.imageUrl = ChatCompletionContentPart.ChatCompletionContentPartImageURL(
                this.imageUrl.url,
                this.imageUrl.detail
            )
        }
        is ContentPart.VideoUrlPart -> {
            part.type = "video_url"
            part.videoUrl = ChatCompletionContentPart.ChatCompletionContentPartVideoURL(
                this.videoUrl.url,
                this.fps ?: 0.0
            )
        }
    }
    return part
}

/**
 * 扩展函数：批量转换
 */
fun List<ChatCompletionContentPart>.toContentParts(): List<ContentPart> {
    return this.map { it.toContentPart() }
}

/**
 * 扩展函数：批量转换
 */
fun List<ContentPart>.toChatCompletionContentParts(): List<ChatCompletionContentPart> {
    return this.map { it.toChatCompletionContentPart() }
}
