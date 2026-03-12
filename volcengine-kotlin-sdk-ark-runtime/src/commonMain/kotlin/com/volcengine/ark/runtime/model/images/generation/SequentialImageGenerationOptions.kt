package com.volcengine.ark.runtime.model.images.generation

import kotlinx.serialization.Serializable

@Serializable
data class SequentialImageGenerationOptions(
    var maxImages: Int? = null
)