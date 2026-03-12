package com.volcengine.ark.runtime.model.multimodalembeddings

import kotlinx.serialization.Serializable

@Serializable
class MultiModalEmbeddingContentPartVideoURL(
    val url: String? = null,
    val fps: Double = 0.0,
)