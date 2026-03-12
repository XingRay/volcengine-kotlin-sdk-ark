package com.volcengine.ark.runtime.model.completion.chat

import com.fasterxml.jackson.annotation.JsonIgnore

@JsonIgnoreProperties(ignoreUnknown = true)
class ChatFunctionProperty {
    @JsonIgnore
    var name: String? = null

    var type: String? = null

    @JsonIgnore
    var required: Boolean? = null

    var description: String? = null

    var items: ChatFunctionProperty? = null

    @JsonProperty("enum")
    var enumValues: Set<*>? = null

    fun builder(): Builder {
        return com.volcengine.ark.runtime.model.completion.chat.ChatFunctionProperty.Builder()
    }

    @Override
    fun toString(): String? {
        return "ChatFunctionProperty{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", required=" + required +
                ", description='" + description + '\'' +
                ", items=" + items +
                ", enumValues=" + enumValues +
                '}'
    }

    class Builder {
        private var name: String? = null
        private var type: String? = null
        private var required: Boolean? = null
        private var description: String? = null
        private var items: ChatFunctionProperty? = null
        private var enumValues: Set<*>? = null

        fun name(name: String?): Builder {
            this.name = name
            return this
        }

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun required(required: Boolean?): Builder {
            this.required = required
            return this
        }

        fun description(description: String?): Builder {
            this.description = description
            return this
        }

        fun items(items: ChatFunctionProperty?): Builder {
            this.items = items
            return this
        }

        fun enumValues(enumValues: Set<*>?): Builder {
            this.enumValues = enumValues
            return this
        }

        fun build(): ChatFunctionProperty {
            val chatFunctionProperty: ChatFunctionProperty = com.volcengine.ark.runtime.model.completion.chat.ChatFunctionProperty()
            chatFunctionProperty.name = name
            chatFunctionProperty.type = type
            chatFunctionProperty.required = required
            chatFunctionProperty.description = description
            chatFunctionProperty.items = items
            chatFunctionProperty.enumValues = enumValues
            return chatFunctionProperty
        }
    }
}