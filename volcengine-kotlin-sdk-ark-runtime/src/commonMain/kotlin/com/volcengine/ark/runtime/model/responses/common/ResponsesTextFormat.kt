package com.volcengine.ark.runtime.model.responses.common

import com.fasterxml.jackson.annotation.JsonProperty

class ResponsesTextFormat {
    @JsonProperty("type")
    var type: String? = null

    @JsonProperty("name")
    var name: String? = null

    @JsonProperty("schema")
    private var schema: JsonNode? = null

    fun getSchema(): JsonNode? {
        return schema
    }

    fun setSchema(schema: JsonNode?) {
        this.schema = schema
    }

    @Override
    fun toString(): String? {
        return "ResponsesTextFormat{" +
                "type='" + type + '\'' +
                '}'
    }

    class Builder {
        private var type: String? = null
        private var name: String? = null
        private var schema: JsonNode? = null

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun name(name: String?): Builder {
            this.name = name
            return this
        }

        fun schema(schema: JsonNode?): Builder {
            this.schema = schema
            return this
        }

        fun build(): ResponsesTextFormat {
            val responsesTextFormat: ResponsesTextFormat = com.volcengine.ark.runtime.model.responses.common.ResponsesTextFormat()
            responsesTextFormat.type = type
            responsesTextFormat.name = name
            responsesTextFormat.setSchema(schema)
            return responsesTextFormat
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.common.ResponsesTextFormat.Builder()
        }
    }
}