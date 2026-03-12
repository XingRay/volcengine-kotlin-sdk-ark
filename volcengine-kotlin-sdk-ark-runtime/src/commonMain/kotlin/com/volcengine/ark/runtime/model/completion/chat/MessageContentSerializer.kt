package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive

// 多态序列化器：自动判断content是字符串还是数组，解析为对应子类
object MessageContentSerializer : JsonContentPolymorphicSerializer<MessageContent>(MessageContent::class) {
    override fun selectDeserializer(element: JsonElement): kotlinx.serialization.DeserializationStrategy<out MessageContent> {
        return when {
            // 如果是字符串 -> 解析为TextContent
            element.jsonPrimitive.isString -> {
                MessageContent.TextContent.serializer()
            }
            // 如果是数组 -> 解析为MultiContent
            element.jsonArray.isNotEmpty() -> {
                MessageContent.MultiContent.serializer()
            }
            else -> throw IllegalArgumentException("不支持的content类型: ${element::class.simpleName}")
        }
    }
}