package com.volcengine.ark.runtime.model.multimodalembeddings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MultimodalEmbedding(
    /**
     * The type of object returned, should be "embedding"
     */
    val `object`: String? = null,

    /**
     * The embedding vector
     */
    val embedding: List<Double?>? = null,

    @SerialName("sparse_embedding")
    val sparseEmbedding: List<SparseEmbedding?>? = null
)
