package com.volcengine.ark.runtime.model.responses.item
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@JsonDeserialize
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
@Serializable
class ItemOutputMessage : BaseMessageItem(), OutputItem {
    @SerialName("content")
    private var content: List<OutputContentItem?>? = null

    @SerialName("status")
    var status: String? = null

    @SerialName("partial")
    var partial: Boolean? = null

    fun getContent(): List<OutputContentItem?>? {
        return content
    }

    fun setContent(content: List<OutputContentItem?>?) {
        this.content = content
    }


    class Builder {
        private var role: String? = null
        private var content: List<OutputContentItem?>? = null
        private var status: String? = null
        private var id: String? = null
        private var partial: Boolean? = null

        fun role(role: String?): Builder {
            this.role = role
            return this
        }

        fun content(content: List<OutputContentItem?>?): Builder {
            this.content = content
            return this
        }

        fun status(status: String?): Builder {
            this.status = status
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

        fun build(): ItemOutputMessage {
            val itemOutputMessage: ItemOutputMessage = com.volcengine.ark.runtime.model.responses.item.ItemOutputMessage()
            itemOutputMessage.setRole(role)
            itemOutputMessage.setContent(content)
            itemOutputMessage.status = status
            itemOutputMessage.setId(id)
            itemOutputMessage.partial = partial
            return itemOutputMessage
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.ItemOutputMessage.Builder()
        }
    }
}