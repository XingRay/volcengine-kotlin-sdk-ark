package com.volcengine.ark.runtime.model.responses.tool.websearch
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class Action {
    @SerialName("query")
    var query: String? = null

    @SerialName("type")
    var type: String? = null


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