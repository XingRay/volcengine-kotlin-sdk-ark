package com.volcengine.ark.runtime.model.responses.tool
import kotlinx.serialization.Serializable


@JsonSerialize(using = com.volcengine.ark.runtime.model.responses.tool.ResponsesToolChoice.ResponsesToolChoiceSerializer::class)
@JsonDeserialize(using = com.volcengine.ark.runtime.model.responses.tool.ResponsesToolChoice.ResponsesToolChoiceDeserializer::class)
@Serializable
class ResponsesToolChoice {
    var mode: String? = null
    private var functionToolChoice: FunctionToolChoice? = null

    fun getFunctionToolChoice(): FunctionToolChoice? {
        return functionToolChoice
    }

    fun setFunctionToolChoice(functionToolChoice: FunctionToolChoice?) {
        this.functionToolChoice = functionToolChoice
    }


    class ResponsesToolChoiceSerializer : JsonSerializer<ResponsesToolChoice?>() {
        @Override
        @Throws(IOException::class)
        fun serialize(value: ResponsesToolChoice, gen: JsonGenerator, serializers: SerializerProvider?) {
            if (value.mode != null) {
                gen.writeString(value.mode)
            } else if (value.functionToolChoice != null) {
                gen.writeObject(value.functionToolChoice)
            } else {
                gen.writeNull()
            }
        }
    }

    class ResponsesToolChoiceDeserializer : JsonDeserializer<ResponsesToolChoice?>() {
        @Override
        @Throws(IOException::class, JsonProcessingException::class)
        fun deserialize(p: JsonParser, ctxt: DeserializationContext?): ResponsesToolChoice? {
            val node: JsonNode = p.getCodec().readTree(p)
            if (node.isTextual()) {
                return com.volcengine.ark.runtime.model.responses.tool.ResponsesToolChoice.Companion.builder().mode(node.asText()).build()
            } else if (node.isObject()) {
                val functionToolChoice: FunctionToolChoice? = p.getCodec().treeToValue(node, FunctionToolChoice::class.java)
                return com.volcengine.ark.runtime.model.responses.tool.ResponsesToolChoice.Companion.builder().functionToolChoice(functionToolChoice).build()
            } else {
                // avoid exception
                return null
            }
        }
    }

    class Builder {
        private var mode: String? = null
        private var functionToolChoice: FunctionToolChoice? = null

        fun mode(mode: String?): Builder {
            this.mode = mode
            return this
        }

        fun functionToolChoice(functionToolChoice: FunctionToolChoice?): Builder {
            this.functionToolChoice = functionToolChoice
            return this
        }

        fun build(): ResponsesToolChoice {
            val responsesToolChoice: ResponsesToolChoice = com.volcengine.ark.runtime.model.responses.tool.ResponsesToolChoice()
            responsesToolChoice.mode = mode
            responsesToolChoice.setFunctionToolChoice(functionToolChoice)
            return responsesToolChoice
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.ResponsesToolChoice.Builder()
        }
    }
}