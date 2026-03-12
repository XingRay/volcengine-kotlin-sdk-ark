package com.volcengine.ark.runtime.model.embeddings


import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class EmbeddingResult {
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
    var data: List<Embedding?>? = null

    /**
     * The API usage for this request
     */
    var usage: Usage? = null

    fun getData(): List<Embedding?>? {
        return data
    }

    fun setData(data: List<Embedding?>?) {
        this.data = data
    }

    fun getUsage(): Usage? {
        return usage
    }

    fun setUsage(usage: Usage?) {
        this.usage = usage
    }

    @Override
    fun toString(): String? {
        return "EmbeddingResult{" +
                "id='" + id + '\'' +
                ", created='" + created + '\'' +
                ", model='" + model + '\'' +
                ", object='" + this.`object` + '\'' +
                ", data=" + data +
                ", usage=" + usage +
                '}'
    }
}
