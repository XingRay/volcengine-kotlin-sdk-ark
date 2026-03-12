package com.volcengine.ark.runtime.model.multimodalembeddings

class SparseEmbeddingInput {
    var type: String? = null

    @Override
    fun toString(): String? {
        return "SparseEmbeddingInput{" +
                "type='" + type + '\'' +
                '}'
    }

    class Builder private constructor() {
        private var type: String? = null

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun build(): SparseEmbeddingInput {
            val sparseEmbeddingInput: SparseEmbeddingInput = com.volcengine.ark.runtime.model.multimodalembeddings.SparseEmbeddingInput()
            sparseEmbeddingInput.type = type
            return sparseEmbeddingInput
        }

        companion object {
            fun SparseEmbeddingInput(): Builder {
                return com.volcengine.ark.runtime.model.multimodalembeddings.SparseEmbeddingInput.Builder()
            }
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.multimodalembeddings.SparseEmbeddingInput.Builder()
        }
    }
}
