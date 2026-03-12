package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement


@Serializable
data class ChatFunction(
    /**
     * The name of the function being called.
     */
    val name: String? = null,

    /**
     * A description of what the function does, used by the model to choose when and how to call the function.
     */
    val description: String? = null,

    /**
     * The parameters the functions accepts.
     */
    @SerialName("parameters")
    val parameters: JsonElement? = null
) {
    class Builder {
        private var name: String? = null
        private var description: String? = null
        private var parameters: JsonElement? = null

        fun name(name: String?): Builder {
            this.name = name
            return this
        }

        fun description(description: String?): Builder {
            this.description = description
            return this
        }

        fun parameters(parameters: JsonElement?): Builder {
            this.parameters = parameters
            return this
        }

        fun build(): ChatFunction {
            return ChatFunction(
                name = name,
                description = description,
                parameters = parameters,
            )
        }
    }
}