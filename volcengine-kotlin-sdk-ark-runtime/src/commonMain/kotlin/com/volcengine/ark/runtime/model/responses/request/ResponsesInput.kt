package com.volcengine.ark.runtime.model.responses.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(using = com.volcengine.ark.runtime.model.responses.request.ResponsesInput.ResponsesInputSerializer::class)
class ResponsesInput {
    var stringValue: String? = null
    private var listValue: List<InputItem?>? = null

    fun getListValue(): List<InputItem?>? {
        return listValue
    }

    fun setListValue(listValue: List<InputItem?>?) {
        this.listValue = listValue
    }

    class ResponsesInputSerializer : JsonSerializer<ResponsesInput?>() {
        @Override
        @Throws(IOException::class)
        fun serialize(value: ResponsesInput, gen: JsonGenerator, serializers: SerializerProvider?) {
            if (value.stringValue != null) {
                gen.writeString(value.stringValue)
            } else if (value.listValue != null) {
                gen.writeObject(value.listValue)
            } else {
                gen.writeNull()
            }
        }
    }

    class Builder {
        private var stringValue: String? = null
        private var listValue: List<InputItem?>? = null

        fun stringValue(stringValue: String?): Builder {
            this.stringValue = stringValue
            return this
        }

        fun listValue(listValue: List<InputItem?>?): Builder {
            this.listValue = listValue
            return this
        }

        fun addListItem(listItem: InputItem?): Builder {
            if (this.listValue == null) {
                this.listValue = ArrayList()
            }
            this.listValue.add(listItem)
            return this
        }

        fun build(): ResponsesInput {
            val responsesInput: ResponsesInput = com.volcengine.ark.runtime.model.responses.request.ResponsesInput()
            responsesInput.stringValue = stringValue
            responsesInput.setListValue(listValue)
            return responsesInput
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.request.ResponsesInput.Builder()
        }
    }
}