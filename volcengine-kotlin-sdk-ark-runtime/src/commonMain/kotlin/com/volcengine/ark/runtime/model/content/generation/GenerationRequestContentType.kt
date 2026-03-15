package com.volcengine.ark.runtime.model.content.generation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class GenerationRequestContentType(val value: String) {

    /**
     * 输入给模型生成视频的内容，文本内容部分。
     */
    @SerialName("text")
    TEXT("text"),

    /**
     * 输入给模型生成视频的内容，图片信息部分。
     */
    @SerialName("image_url")
    IMAGE_URL("image_url"),

    /**
     * 基于样片任务 ID，生成正式视频。仅 Seedance 1.5 pro 支持该功能
     */
    @SerialName("draft_task")
    DRAFT_TASK("draft_task"),

}