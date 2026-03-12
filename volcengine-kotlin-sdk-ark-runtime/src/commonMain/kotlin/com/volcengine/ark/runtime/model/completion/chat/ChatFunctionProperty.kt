package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
data class ChatFunctionProperty(
    @Transient
    val name: String? = null,

    val type: String? = null,

    @Transient
    val required: Boolean? = null,

    val description: String? = null,

    val items: ChatFunctionProperty? = null,

    @SerialName("enum")
    val enumValues: Set<*>? = null
) {
    fun builder(): Builder {
        return Builder()
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
            return ChatFunctionProperty(
                name = name,
                type = type,
                required = required,
                description = description,
                items = items,
                enumValues = enumValues,
            )
        }
    }
}