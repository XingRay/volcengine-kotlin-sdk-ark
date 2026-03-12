package com.volcengine.ark.runtime.model.multimodalembeddings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MultimodalEmbeddingRequest(
    /**
     * The name of the model to use.
     * Required if using the new v1/embeddings endpoint.
     */
    val model: String? = null,

    /**
     * Input text to get embeddings for, encoded as a string or array of tokens.
     * To get embeddings for multiple inputs in a single request, pass an array of strings or array of token arrays.
     * Each input must not exceed 2048 tokens in length.
     *
     *
     * Unless you are embedding code, we suggest replacing newlines (\n) in your input with a single space,
     * as we have observed inferior results when newlines are present.
     */
    val input: List<MultimodalEmbeddingInput?>? = null,

    @SerialName("encoding_format")
    val encodingFormat: String? = null,

    @SerialName("dimensions")
    val dimensions: Int? = null,

    @SerialName("sparse_embedding")
    val sparseEmbedding: SparseEmbeddingInput? = null
) {

    companion object {

        class Builder {
            private var model: String? = null
            private var input: List<MultimodalEmbeddingInput?>? = null
            private var encodingFormat: String? = null
            private var dimensions: Int? = null
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

            fun dimensions(dimensions: Int?): Builder {
                this.dimensions = dimensions
                return this
            }

            fun sparseEmbedding(sparseEmbedding: SparseEmbeddingInput?): Builder {
                this.sparseEmbedding = sparseEmbedding
                return this
            }

            fun build(): MultimodalEmbeddingRequest {
                return MultimodalEmbeddingRequest(
                    model = model,
                    input = input,
                    encodingFormat = encodingFormat,
                    dimensions = dimensions,
                    sparseEmbedding = sparseEmbedding,
                )
            }
        }

        fun builder(): Builder {
            return Builder()
        }

    }
}
