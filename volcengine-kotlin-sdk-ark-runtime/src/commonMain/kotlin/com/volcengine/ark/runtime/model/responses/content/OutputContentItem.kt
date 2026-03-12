package com.volcengine.ark.runtime.model.responses.content

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = OutputContentItemPolymorphicSerializer::class)
abstract class OutputContentItem(
    @SerialName("type")
    open val type: String
) {
    // 空构造函数（兼容序列化/反序列化）
    constructor() : this("")
}