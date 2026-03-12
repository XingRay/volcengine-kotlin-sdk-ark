package com.volcengine.ark.runtime.model.responses.common

import com.fasterxml.jackson.annotation.JsonProperty

class Error {
    @JsonProperty("code")
    var code: String? = null

    @JsonProperty("message")
    var message: String? = null

    @Override
    fun toString(): String? {
        return "Error{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}'
    }

    class Builder {
        private var code: String? = null
        private var message: String? = null

        fun code(code: String?): Builder {
            this.code = code
            return this
        }

        fun message(message: String?): Builder {
            this.message = message
            return this
        }

        fun build(): Error {
            val error: Error = com.volcengine.ark.runtime.model.responses.common.Error()
            error.code = code
            error.message = message
            return error
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.common.Error.Builder()
        }
    }
}