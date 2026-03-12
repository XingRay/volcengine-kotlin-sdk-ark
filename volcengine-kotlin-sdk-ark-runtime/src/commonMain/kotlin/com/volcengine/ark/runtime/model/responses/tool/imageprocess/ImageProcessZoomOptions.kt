package com.volcengine.ark.runtime.model.responses.tool.imageprocess
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ImageProcessZoomOptions {
    @SerialName("type")
    var type: String? = null


    class Builder {
        private var type: String? = null

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun build(): ImageProcessZoomOptions {
            val imageProcessZoomOptions: ImageProcessZoomOptions = com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessZoomOptions()
            imageProcessZoomOptions.type = type
            return imageProcessZoomOptions
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessZoomOptions.Builder()
        }
    }
}