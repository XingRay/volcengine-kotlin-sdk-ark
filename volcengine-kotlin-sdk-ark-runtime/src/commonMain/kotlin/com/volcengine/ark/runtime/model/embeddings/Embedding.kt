package com.volcengine.ark.runtime.model.embeddings

import kotlinx.serialization.Serializable


@Serializable
data class Embedding(
    /**
     * The type of object returned, should be "embedding"
     */
    val `object`: String? = null,

    /**
     * The embedding vector
     */
    val embedding: List<Double>? = null,

    /**
     * The position of this embedding in the list
     */
    val index: Int? = null
)
