package com.volcengine.ark.runtime.model.responses.tool

import com.fasterxml.jackson.annotation.JsonProperty

class FunctionToolChoice {
    @JsonProperty("type")
    var type: String? = null

    @JsonProperty("name")
    var name: String? = null

    @Override
    fun toString(): String? {
        return "FunctionToolChoice{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}'
    }

    class Builder {
        private var type: String? = null
        private var name: String? = null

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun name(name: String?): Builder {
            this.name = name
            return this
        }

        fun build(): FunctionToolChoice {
            val functionToolChoice: FunctionToolChoice = com.volcengine.ark.runtime.model.responses.tool.FunctionToolChoice()
            functionToolChoice.type = type
            functionToolChoice.name = name
            return functionToolChoice
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.FunctionToolChoice.Builder()
        }
    }
}