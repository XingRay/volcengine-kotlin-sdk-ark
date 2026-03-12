package com.volcengine.ark.runtime.model.responses.tool.imageprocess
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ImageProcessError {
    @SerialName("message")
    var message: String? = null


    class Builder {
        private var message: String? = null

        fun message(message: String?): Builder {
            this.message = message
            return this
        }

        fun build(): ImageProcessError {
            val imageProcessError: ImageProcessError = com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessError()
            imageProcessError.message = message
            return imageProcessError
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessError.Builder()
        }
    }
}