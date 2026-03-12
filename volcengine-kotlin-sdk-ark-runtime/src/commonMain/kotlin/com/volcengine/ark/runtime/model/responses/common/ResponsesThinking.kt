package com.volcengine.ark.runtime.model.responses.common

import com.fasterxml.jackson.annotation.JsonProperty

class ResponsesThinking {
    @JsonProperty("type")
    var type: String? = null

    @Override
    fun toString(): String? {
        return "ResponsesThinking{" +
                "type='" + type + '\'' +
                '}'
    }

    class Builder {
        private var type: String? = null

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun build(): ResponsesThinking {
            val responsesThinking: ResponsesThinking = com.volcengine.ark.runtime.model.responses.common.ResponsesThinking()
            responsesThinking.type = type
            return responsesThinking
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.common.ResponsesThinking.Builder()
        }
    }
}