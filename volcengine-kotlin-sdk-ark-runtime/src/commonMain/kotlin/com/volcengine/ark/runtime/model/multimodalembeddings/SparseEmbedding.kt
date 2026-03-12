package com.volcengine.ark.runtime.model.multimodalembeddings

class SparseEmbedding {
    private var index: Integer? = null
    var value: Double? = null

    fun getIndex(): Integer? {
        return index
    }

    fun setIndex(index: Integer?) {
        this.index = index
    }

    @Override
    fun toString(): String? {
        return "SparseEmbedding{" +
                "index=" + index +
                ", value=" + value +
                '}'
    }

    class Builder private constructor() {
        private var index: Integer? = null
        private var value: Double? = null
        fun index(index: Integer?): Builder {
            this.index = index
            return this
        }

        fun value(value: Double?): Builder {
            this.value = value
            return this
        }

        fun build(): SparseEmbedding {
            val sparseEmbedding: SparseEmbedding = com.volcengine.ark.runtime.model.multimodalembeddings.SparseEmbedding()
            sparseEmbedding.setIndex(index)
            sparseEmbedding.value = value
            return sparseEmbedding
        }

        companion object {
            fun SparseEmbedding(): Builder {
                return com.volcengine.ark.runtime.model.multimodalembeddings.SparseEmbedding.Builder()
            }
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.multimodalembeddings.SparseEmbedding.Builder()
        }
    }
}
