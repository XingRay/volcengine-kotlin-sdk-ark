package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ThinkingType {
    // 强制开启，强制开启深度思考能力。
    @SerialName("enabled")
    ENABLED,

    // 强制关闭深度思考能力。
    @SerialName("disabled")
    DISABLED,

    // 模型自行判断是否进行深度思考。
    @SerialName("auto")
    AUTO,

}

@Serializable
data class ChatCompletionRequestThinking(
    val type: ThinkingType? = null,
)
