package com.volcengine.ark.runtime.model.responses.content

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// ===================== 抽象父类：InputContentItem =====================
@Serializable(with = InputContentItemPolymorphicSerializer::class)
abstract class InputContentItem(
    @SerialName("type")
    open val type: String
) {
    // 空构造函数（兼容序列化）
    constructor() : this("")
}