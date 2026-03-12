package com.volcengine.ark.runtime.model.embeddings


class EmbeddingRequest {
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
    var input: List<String?>? = null

    /**
     * A unique identifier representing your end-user, which will help OpenAI to monitor and detect abuse.
     */
    var user: String? = null

    class Builder private constructor() {
        private var model: String? = null
        private var input: List<String?>? = null
        private var user: String? = null

        fun model(model: String?): Builder {
            this.model = model
            return this
        }

        fun input(input: List<String?>?): Builder {
            this.input = input
            return this
        }

        fun user(user: String?): Builder {
            this.user = user
            return this
        }

        fun build(): EmbeddingRequest {
            val embeddingRequest: EmbeddingRequest = com.volcengine.ark.runtime.model.embeddings.EmbeddingRequest()
            embeddingRequest.model = model
            embeddingRequest.input = input
            embeddingRequest.user = user
            return embeddingRequest
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.embeddings.EmbeddingRequest.Builder()
        }
    }
}
