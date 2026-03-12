package com.volcengine.ark.runtime.model.responses.tool.imageprocess
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ImageProcessRotateOptions {
    @SerialName("type")
    var type: String? = null


    class Builder {
        private var type: String? = null

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun build(): ImageProcessRotateOptions {
            val imageProcessRotateOptions: ImageProcessRotateOptions = com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessRotateOptions()
            imageProcessRotateOptions.type = type
            return imageProcessRotateOptions
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessRotateOptions.Builder()
        }
    }
}