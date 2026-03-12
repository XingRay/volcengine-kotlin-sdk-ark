package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.*
import kotlinx.serialization.json.*

// 1. 定义 toolChoice 的密封类（覆盖 String 和 ToolChoice 对象两种类型）
@Serializable
sealed class ChatToolChoice {
    // 字符串类型（如 "auto"/"none" 等）
    @Serializable
    data class StringValue(val value: String) : ChatToolChoice()

    // 自定义对象类型（对应原 ChatCompletionRequestToolChoice）
    @Serializable
    data class ObjectValue(
        val type: String? = null,
        val function: ChatCompletionRequestToolChoiceFunction? = null,
    ) : ChatToolChoice()

    // 辅助：快速创建 String 类型的 toolChoice
    companion object {
        fun fromString(value: String): ChatToolChoice = StringValue(value)

        // 辅助：快速创建 Object 类型的 toolChoice
        fun fromObject(type: String, functionName: String): ChatToolChoice = ObjectValue(
            type = type,
            function = ChatCompletionRequestToolChoiceFunction(functionName)
        )
    }
}