package com.volcengine.ark.runtime.model.multimodalembeddings

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class MultimodalEmbedding {
    /**
     * The type of object returned, should be "embedding"
     */
    var `object`: String? = null
        set(object) {
            field = this.`object`
        }

    /**
     * The embedding vector
     */
    var embedding: List<Double?>? = null

    @JsonProperty("sparse_embedding")
    private var sparseEmbedding: List<SparseEmbedding?>? = null

    fun getSparseEmbedding(): List<SparseEmbedding?>? {
        return sparseEmbedding
    }

    fun setSparseEmbedding(sparseEmbedding: List<SparseEmbedding?>?) {
        this.sparseEmbedding = sparseEmbedding
    }

    @Override
    fun toString(): String? {
        return "Embedding{" +
                "object='" + this.`object` + '\'' +
                ", embedding=" + embedding +
                ", sparseEmbedding=" + sparseEmbedding +
                '}'
    }
}
