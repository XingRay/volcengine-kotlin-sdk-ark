package com.volcengine.ark.runtime.model.embeddings

import com.volcengine.ark.runtime.model.Usage
import kotlinx.serialization.Serializable


@Serializable
data class EmbeddingResult(
    /**
     * Unique id assigned to this embedding
     */
    val id: String? = null,

    /**
     * The creation time in epoch seconds.
     */
    val created: String? = null,

    /**
     * The model used for generating embeddings
     */
    val model: String? = null,

    /**
     * The type of object returned, should be "list"
     */
    val `object`: String? = null,

    /**
     * A list of the calculated embeddings
     */
    val data: List<Embedding?>? = null,

    /**
     * The API usage for this request
     */
    val usage: Usage? = null
)
