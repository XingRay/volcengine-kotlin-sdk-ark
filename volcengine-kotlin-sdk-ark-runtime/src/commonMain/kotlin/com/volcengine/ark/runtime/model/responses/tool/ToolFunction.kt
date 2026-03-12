package com.volcengine.ark.runtime.model.responses.tool
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ToolFunction : ResponsesTool(ResponsesConstants.TOOL_TYPE_FUNCTION) {
    @SerialName("name")
    var name: String? = null

    @SerialName("strict")
    var strict: Boolean? = null

    @SerialName("description")
    var description: String? = null

    @SerialName("parameters")
    private var parameters: JsonNode? = null

    fun getParameters(): JsonNode? {
        return parameters
    }

    fun setParameters(parameters: JsonNode?) {
        this.parameters = parameters
    }


    class Builder {
        private var name: String? = null
        private var strict: Boolean? = null
        private val type: String? = null
        private var description: String? = null
        private var parameters: JsonNode? = null

        fun name(name: String?): Builder {
            this.name = name
            return this
        }

        fun strict(strict: Boolean?): Builder {
            this.strict = strict
            return this
        }

        fun description(description: String?): Builder {
            this.description = description
            return this
        }

        fun parameters(parameters: JsonNode?): Builder {
            this.parameters = parameters
            return this
        }

        fun build(): ToolFunction {
            val toolFunction: ToolFunction = com.volcengine.ark.runtime.model.responses.tool.ToolFunction()
            toolFunction.name = name
            toolFunction.strict = strict
            toolFunction.description = description
            toolFunction.setParameters(parameters)
            return toolFunction
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.ToolFunction.Builder()
        }
    }
}