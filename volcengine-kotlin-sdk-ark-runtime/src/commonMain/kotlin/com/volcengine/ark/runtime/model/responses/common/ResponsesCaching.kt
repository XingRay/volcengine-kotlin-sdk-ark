package com.volcengine.ark.runtime.model.responses.common

import com.fasterxml.jackson.annotation.JsonProperty

class ResponsesCaching {
    @JsonProperty("type")
    var type: String? = null


    @JsonProperty("prefix")
    var prefix: Boolean? = null

    @Override
    fun toString(): String {
        val sb = StringBuilder("ResponsesCaching{")
        sb.append("type='").append(type).append('\'')
        if (prefix != null) {
            sb.append(", prefix=").append(prefix)
        }
        sb.append('}')
        return sb.toString()
    }

    class Builder {
        private var type: String? = null
        private var prefix: Boolean? = null

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun prefix(prefix: Boolean?): Builder {
            this.prefix = prefix
            return this
        }

        fun build(): ResponsesCaching {
            val responsesCaching: ResponsesCaching = com.volcengine.ark.runtime.model.responses.common.ResponsesCaching()
            responsesCaching.type = type
            responsesCaching.prefix = prefix
            return responsesCaching
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.common.ResponsesCaching.Builder()
        }
    }
}