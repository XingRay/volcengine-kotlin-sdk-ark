package com.volcengine.ark.runtime.model.tokenization

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class TokenizationResult {
    /**
     * Unique id assigned to this tokenization
     */
    var id: String? = null

    /**
     * The creation time in epoch seconds.
     */
    var created: String? = null

    /**
     * The model used for generating tokenization
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
     * A list of the calculated tokenization
     */
    var data: List<Tokenization?>? = null

    fun getData(): List<Tokenization?>? {
        return data
    }

    fun setData(data: List<Tokenization?>?) {
        this.data = data
    }

    @Override
    fun toString(): String? {
        return "TokenizationResult{" +
                "id='" + id + '\'' +
                ", created='" + created + '\'' +
                ", model='" + model + '\'' +
                ", object='" + this.`object` + '\'' +
                ", data=" + data +
                '}'
    }
}
