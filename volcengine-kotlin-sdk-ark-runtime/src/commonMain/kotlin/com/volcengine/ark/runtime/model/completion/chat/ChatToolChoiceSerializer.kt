package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

// 3. 自定义多态序列化器（核心：自动识别 JSON 类型并解析）
object ChatToolChoiceSerializer : JsonContentPolymorphicSerializer<ChatToolChoice>(ChatToolChoice::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<ChatToolChoice> {
        return when (element) {
            // 如果 JSON 是字符串 → 解析为 StringValue
            is JsonPrimitive if element.isString -> ChatToolChoice.StringValue.serializer()
            // 如果 JSON 是对象 → 解析为 ObjectValue
            is JsonObject -> ChatToolChoice.ObjectValue.serializer()
            // 其他类型抛异常（符合原 Java 逻辑）
            else -> error("不支持的 toolChoice 类型：${element::class.simpleName}")
        }
    }
}