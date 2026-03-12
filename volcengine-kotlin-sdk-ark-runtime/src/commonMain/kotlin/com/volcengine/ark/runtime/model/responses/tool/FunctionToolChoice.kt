package com.volcengine.ark.runtime.model.responses.tool
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class FunctionToolChoice {
    @SerialName("type")
    var type: String? = null

    @SerialName("name")
    var name: String? = null


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