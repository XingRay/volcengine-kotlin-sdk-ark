package com.volcengine.ark.runtime.model.responses.tool.websearch

import com.fasterxml.jackson.annotation.JsonProperty

class Action {
    @JsonProperty("query")
    var query: String? = null

    @JsonProperty("type")
    var type: String? = null

    @Override
    fun toString(): String? {
        return "Action{" +
                "query='" + query + '\'' +
                ", type='" + type + '\'' +
                '}'
    }

    class Builder {
        private var query: String? = null
        private var type: String? = null

        fun query(query: String?): Builder {
            this.query = query
            return this
        }

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun build(): Action {
            val action: Action = com.volcengine.ark.runtime.model.responses.tool.websearch.Action()
            action.query = query
            action.type = type
            return action
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.websearch.Action.Builder()
        }
    }
}