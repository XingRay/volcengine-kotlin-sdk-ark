package com.volcengine.ark.runtime.model.multimodalembeddings

import kotlinx.serialization.Serializable

@Serializable
class MultiModalEmbeddingContentPartImageURL(
    val url: String? = null,
    val detail: String? = null,
)