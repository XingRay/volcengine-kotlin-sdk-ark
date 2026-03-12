package com.volcengine.ark.runtime.model.responses.tool.imageprocess
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ImageProcessPointOptions {
    @SerialName("type")
    var type: String? = null


    class Builder {
        private var type: String? = null

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun build(): ImageProcessPointOptions {
            val imageProcessPointOptions: ImageProcessPointOptions = com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessPointOptions()
            imageProcessPointOptions.type = type
            return imageProcessPointOptions
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessPointOptions.Builder()
        }
    }
}