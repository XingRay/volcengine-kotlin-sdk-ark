package com.volcengine.ark.runtime.model.responses.item

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ItemImageProcess : BaseItem(ResponsesConstants.ITEM_TYPE_IMAGE_PROCESS), InputItem, OutputItem {
    @JsonProperty("action")
    private var action: ImageProcessAction? = null

    @JsonProperty("arguments")
    private var arguments: ImageProcessArguments? = null

    @JsonProperty("status")
    var status: String? = null

    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("error")
    private var error: ImageProcessError? = null

    fun getAction(): ImageProcessAction? {
        return action
    }

    fun setAction(action: ImageProcessAction?) {
        this.action = action
    }

    fun getArguments(): ImageProcessArguments? {
        return arguments
    }

    fun setArguments(arguments: ImageProcessArguments?) {
        this.arguments = arguments
    }

    fun getError(): ImageProcessError? {
        return error
    }

    fun setError(error: ImageProcessError?) {
        this.error = error
    }

    @Override
    fun toString(): String? {
        return "ItemImageProcess{" +
                "type='" + getType() + '\'' +
                ", action=" + action +
                ", arguments=" + arguments +
                ", status='" + status + '\'' +
                ", id='" + id + '\'' +
                ", error=" + error +
                '}'
    }

    class Builder {
        private var action: ImageProcessAction? = null
        private var arguments: ImageProcessArguments? = null
        private var status: String? = null
        private var id: String? = null
        private var error: ImageProcessError? = null

        fun action(action: ImageProcessAction?): Builder {
            this.action = action
            return this
        }

        fun arguments(arguments: ImageProcessArguments?): Builder {
            this.arguments = arguments
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

        fun error(error: ImageProcessError?): Builder {
            this.error = error
            return this
        }

        fun build(): ItemImageProcess {
            val itemImageProcess: ItemImageProcess = com.volcengine.ark.runtime.model.responses.item.ItemImageProcess()
            itemImageProcess.setAction(action)
            itemImageProcess.setArguments(arguments)
            itemImageProcess.status = status
            itemImageProcess.id = id
            itemImageProcess.setError(error)
            return itemImageProcess
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.ItemImageProcess.Builder()
        }
    }
}