package com.volcengine.ark.runtime.model.responses.tool.imageprocess
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ImageProcessGroundingOptions {
    @SerialName("type")
    var type: String? = null


    class Builder {
        private var type: String? = null

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun build(): ImageProcessGroundingOptions {
            val imageProcessGroundingOptions: ImageProcessGroundingOptions = com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessGroundingOptions()
            imageProcessGroundingOptions.type = type
            return imageProcessGroundingOptions
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessGroundingOptions.Builder()
        }
    }
}