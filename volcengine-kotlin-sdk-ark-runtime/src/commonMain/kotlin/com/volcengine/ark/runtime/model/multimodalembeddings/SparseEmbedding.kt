package com.volcengine.ark.runtime.model.multimodalembeddings

import kotlinx.serialization.Serializable

@Serializable
data class SparseEmbedding(
    val index: Int? = null,
    val value: Double? = null
)