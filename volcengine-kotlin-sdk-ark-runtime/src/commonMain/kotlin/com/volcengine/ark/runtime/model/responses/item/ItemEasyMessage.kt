package com.volcengine.ark.runtime.model.responses.item
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@JsonDeserialize
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
@Serializable
class ItemEasyMessage : BaseMessageItem(), InputItem {
    @SerialName("content")
    private var content: MessageContent? = null

    @SerialName("partial")
    var partial: Boolean? = null

    fun getContent(): MessageContent? {
        return content
    }

    fun setContent(content: MessageContent?) {
        this.content = content
    }


    class Builder {
        private var role: String? = null
        private var content: MessageContent? = null
        private var id: String? = null

        private var partial: Boolean? = null

        fun role(role: String?): Builder {
            this.role = role
            return this
        }

        fun content(content: MessageContent?): Builder {
            this.content = content
            return this
        }

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun partial(partial: Boolean?): Builder {
            this.partial = partial
            return this
        }

        fun build(): ItemEasyMessage {
            val itemEasyMessage: ItemEasyMessage = com.volcengine.ark.runtime.model.responses.item.ItemEasyMessage()
            itemEasyMessage.setRole(role)
            itemEasyMessage.setContent(content)
            itemEasyMessage.setId(id)
            itemEasyMessage.partial = partial
            return itemEasyMessage
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.ItemEasyMessage.Builder()
        }
    }
}