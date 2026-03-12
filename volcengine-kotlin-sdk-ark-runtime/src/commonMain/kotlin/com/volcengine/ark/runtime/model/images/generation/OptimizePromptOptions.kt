package com.volcengine.ark.runtime.model.images.generation

import kotlinx.serialization.Serializable

@Serializable
data class OptimizePromptOptions(
    var thinking: String? = null,
    var mode: String? = null
)