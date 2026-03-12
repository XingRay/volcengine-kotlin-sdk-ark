package com.volcengine.ark.runtime.model.multimodalembeddings

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class MultimodalEmbeddingResult {
    /**
     * Unique id assigned to this embedding
     */
    var id: String? = null

    /**
     * The creation time in epoch seconds.
     */
    var created: String? = null

    /**
     * The model used for generating embeddings
     */
    var model: String? = null

    /**
     * The type of object returned, should be "list"
     */
    var `object`: String? = null
        set(object) {
            field = this.`object`
        }

    /**
     * A list of the calculated embeddings
     */
    private var data: MultimodalEmbedding? = null

    /**
     * The API usage for this request
     */
    private var usage: MultimodalEmbeddingUsage? = null

    fun getData(): MultimodalEmbedding? {
        return data
    }

    fun setData(data: MultimodalEmbedding?) {
        this.data = data
    }

    fun getUsage(): MultimodalEmbeddingUsage? {
        return usage
    }

    fun setUsage(usage: MultimodalEmbeddingUsage?) {
        this.usage = usage
    }

    @Override
    fun toString(): String? {
        return "EmbeddingResult{" + "id='" + id + '\'' + ", created='" + created + '\'' + ", model='" + model + '\'' + ", object='" + this.`object` + '\'' + ", data=" + data + ", usage=" + usage + '}'
    }
}
