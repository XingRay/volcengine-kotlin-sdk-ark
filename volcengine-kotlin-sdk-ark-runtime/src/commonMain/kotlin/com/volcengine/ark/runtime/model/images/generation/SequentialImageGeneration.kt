package com.volcengine.ark.runtime.model.images.generation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SequentialImageGeneration(val value: String) {
    /**
     * auto：自动判断模式，模型会根据用户提供的提示词自主判断是否返回组图以及组图包含的图片数量。
     */
    @SerialName("auto")
    AUTO("auto"),

    /**
     * disabled：关闭组图功能，模型只会生成一张图。
     */
    @SerialName("disabled")
    DISABLED("disabled"),
}