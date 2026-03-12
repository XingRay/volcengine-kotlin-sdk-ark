package com.volcengine.ark.runtime.model.embeddings

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Embedding {
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

    /**
     * The position of this embedding in the list
     */
    var index: Integer? = null

    fun getIndex(): Integer? {
        return index
    }

    fun setIndex(index: Integer?) {
        this.index = index
    }

    @Override
    fun toString(): String? {
        return "Embedding{" +
                "object='" + this.`object` + '\'' +
                ", embedding=" + embedding +
                ", index=" + index +
                '}'
    }
}
