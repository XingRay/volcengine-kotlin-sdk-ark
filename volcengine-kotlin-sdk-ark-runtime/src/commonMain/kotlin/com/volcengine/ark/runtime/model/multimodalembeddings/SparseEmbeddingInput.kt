package com.volcengine.ark.runtime.model.multimodalembeddings

import kotlinx.serialization.Serializable

@Serializable
data class SparseEmbeddingInput(
    val type: String? = null
)