package com.volcengine.ark.runtime.model.responses.common

import com.fasterxml.jackson.annotation.JsonProperty

class ResponsesReasoning {
    @JsonProperty("effort")
    var effort: String? = null


    @Override
    fun toString(): String? {
        return "ResponseReasoning{" +
                "effort='" + effort + '\'' +
                '}'
    }

    class Builder {
        private var effort: String? = null

        fun effort(effort: String?): Builder {
            this.effort = effort
            return this
        }

        fun build(): ResponsesReasoning {
            val responsesReasoning: ResponsesReasoning = com.volcengine.ark.runtime.model.responses.common.ResponsesReasoning()
            responsesReasoning.effort = effort
            return responsesReasoning
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.common.ResponsesReasoning.Builder()
        }
    }
}