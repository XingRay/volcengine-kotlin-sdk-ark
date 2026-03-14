package com.volcengine.ark.runtime.model.images.generation

import kotlinx.serialization.Serializable

@Serializable
data class SequentialImageGenerationOptions(

    /**
     * 指定本次请求，最多可生成的图片数量。
     * 取值范围： [1, 15]
     * 说明: 实际可生成的图片数量，除受到 max_images 影响外，还受到输入的参考图数量影响。输入的参考图数量+最终生成的图片数量≤15张。
     */
    var maxImages: Int? = null
)