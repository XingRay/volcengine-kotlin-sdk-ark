package com.volcengine.ark.runtime.model.multimodalembeddings

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class MultimodalEmbeddingRequest {
    /**
     * The name of the model to use.
     * Required if using the new v1/embeddings endpoint.
     */
    var model: String? = null

    /**
     * Input text to get embeddings for, encoded as a string or array of tokens.
     * To get embeddings for multiple inputs in a single request, pass an array of strings or array of token arrays.
     * Each input must not exceed 2048 tokens in length.
     * 
     * 
     * Unless you are embedding code, we suggest replacing newlines (\n) in your input with a single space,
     * as we have observed inferior results when newlines are present.
     */
    private var input: List<MultimodalEmbeddingInput?>? = null

    @JsonProperty("encoding_format")
    var encodingFormat: String? = null

    @JsonProperty("dimensions")
    private var dimensions: Integer? = null

    @JsonProperty("sparse_embedding")
    private var sparseEmbedding: SparseEmbeddingInput? = null


    @Override
    fun toString(): String? {
        return "MultimodalEmbeddingRequest{" +
                "model='" + model + '\'' +
                ", input=" + input +
                ", encodingFormat='" + encodingFormat + '\'' +
                ", dimensions=" + dimensions +
                ", sparseEmbedding=" + sparseEmbedding +
                '}'
    }

    fun getInput(): List<MultimodalEmbeddingInput?>? {
        return input
    }

    fun setInput(input: List<MultimodalEmbeddingInput?>?) {
        this.input = input
    }

    fun setDimensions(dimensions: Integer?) {
        this.dimensions = dimensions
    }

    fun getDimensions(): Integer? {
        return dimensions
    }

    fun setSparseEmbedding(sparseEmbedding: SparseEmbeddingInput?) {
        this.sparseEmbedding = sparseEmbedding
    }

    fun getSparseEmbedding(): SparseEmbeddingInput? {
        return sparseEmbedding
    }

    class Builder private constructor() {
        private var model: String? = null
        private var input: List<MultimodalEmbeddingInput?>? = null
        private var encodingFormat: String? = null
        private var dimensions: Integer? = null
        private var sparseEmbedding: SparseEmbeddingInput? = null

        fun model(model: String?): Builder {
            this.model = model
            return this
        }

        fun input(input: List<MultimodalEmbeddingInput?>?): Builder {
            this.input = input
            return this
        }

        fun encodingFormat(encodingFormat: String?): Builder {
            this.encodingFormat = encodingFormat
            return this
        }

        fun dimensions(dimensions: Integer?): Builder {
            this.dimensions = dimensions
            return this
        }

        fun sparseEmbedding(sparseEmbedding: SparseEmbeddingInput?): Builder {
            this.sparseEmbedding = sparseEmbedding
            return this
        }

        fun build(): MultimodalEmbeddingRequest {
            val embeddingRequest: MultimodalEmbeddingRequest = com.volcengine.ark.runtime.model.multimodalembeddings.MultimodalEmbeddingRequest()
            embeddingRequest.model = model
            embeddingRequest.setInput(input)
            embeddingRequest.encodingFormat = encodingFormat
            embeddingRequest.setDimensions(dimensions)
            embeddingRequest.setSparseEmbedding(sparseEmbedding)
            return embeddingRequest
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.multimodalembeddings.MultimodalEmbeddingRequest.Builder()
        }
    }
}
