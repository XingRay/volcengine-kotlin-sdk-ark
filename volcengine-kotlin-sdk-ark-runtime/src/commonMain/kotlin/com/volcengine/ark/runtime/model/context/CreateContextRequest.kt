package com.volcengine.ark.runtime.model.context

import com.volcengine.ark.runtime.model.completion.chat.ChatMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CreateContextRequest(
    @SerialName("model")
    val model: String? = null,

    @SerialName("mode")
    val mode: String? = null,

    @SerialName("messages")
    val messages: List<ChatMessage>? = null,

    @SerialName("ttl")
    val ttl: Int? = null,

    @SerialName("truncation_strategy")
    val truncationStrategy: TruncationStrategy? = null
) {

    companion object {
        class Builder {
            private var model: String? = null
            private var mode: String? = null
            private var messages: List<ChatMessage>? = null
            private var ttl: Int? = null
            private var truncationStrategy: TruncationStrategy? = null

            fun model(model: String?): Builder {
                this.model = model
                return this
            }

            fun mode(mode: String?): Builder {
                this.mode = mode
                return this
            }

            fun messages(messages: List<ChatMessage>?): Builder {
                this.messages = messages
                return this
            }

            fun ttl(ttl: Int?): Builder {
                this.ttl = ttl
                return this
            }

            fun truncationStrategy(truncationStrategy: TruncationStrategy?): Builder {
                this.truncationStrategy = truncationStrategy
                return this
            }

            fun build(): CreateContextRequest {
                return CreateContextRequest(
                    model = model,
                    mode = mode,
                    messages = messages,
                    ttl = ttl,
                    truncationStrategy = truncationStrategy,
                )
            }
        }

        fun builder(): Builder {
            return Builder()
        }
    }
}
