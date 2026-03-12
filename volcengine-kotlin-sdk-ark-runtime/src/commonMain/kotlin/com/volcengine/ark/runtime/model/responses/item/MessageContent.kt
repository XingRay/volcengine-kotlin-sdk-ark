package com.volcengine.ark.runtime.model.responses.item

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(using = com.volcengine.ark.runtime.model.responses.item.MessageContent.MessageContentSerializer::class)
@JsonDeserialize(using = com.volcengine.ark.runtime.model.responses.item.MessageContent.MessageContentDeserializer::class)
class MessageContent {
    var stringValue: String? = null
    private var listValue: List<InputContentItem?>? = null

    fun getListValue(): List<InputContentItem?>? {
        return listValue
    }

    fun setListValue(listValue: List<InputContentItem?>?) {
        this.listValue = listValue
    }

    class MessageContentSerializer : JsonSerializer<MessageContent?>() {
        @Override
        @Throws(IOException::class)
        fun serialize(value: MessageContent, gen: JsonGenerator, serializers: SerializerProvider?) {
            if (value.stringValue != null) {
                gen.writeString(value.stringValue)
            } else if (value.listValue != null) {
                gen.writeObject(value.listValue)
            } else {
                gen.writeNull()
            }
        }
    }

    class MessageContentDeserializer : JsonDeserializer<MessageContent?>() {
        @Override
        @Throws(IOException::class)
        fun deserialize(p: JsonParser, ctxt: DeserializationContext?): MessageContent {
            val node: JsonNode = p.getCodec().readTree(p)
            val result: MessageContent = com.volcengine.ark.runtime.model.responses.item.MessageContent()

            if (node.isTextual()) {
                result.stringValue = node.asText()
            } else if (node.isArray()) {
                val list: List<InputContentItem?> = ArrayList()
                for (itemNode in node) {
                    val item: InputContentItem? = p.getCodec().treeToValue(itemNode, InputContentItem::class.java)
                    list.add(item)
                }
                result.setListValue(list)
            } else {
                throw IOException("Unexpected JSON type for MessageContent")
            }

            return result
        }
    }

    class Builder {
        private var stringValue: String? = null
        private var listValue: List<InputContentItem?>? = null

        fun stringValue(stringValue: String?): Builder {
            this.stringValue = stringValue
            return this
        }

        fun listValue(listValue: List<InputContentItem?>?): Builder {
            this.listValue = listValue
            return this
        }

        fun addListItem(listValue: InputContentItem?): Builder {
            if (this.listValue == null) {
                this.listValue = ArrayList()
            }
            this.listValue.add(listValue)
            return this
        }

        fun build(): MessageContent {
            val messageContent: MessageContent = com.volcengine.ark.runtime.model.responses.item.MessageContent()
            messageContent.stringValue = stringValue
            messageContent.setListValue(listValue)
            return messageContent
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.MessageContent.Builder()
        }
    }
}