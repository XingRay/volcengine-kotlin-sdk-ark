package com.volcengine.ark.runtime.model.responses.tool.imageprocess
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ImageProcessAction {
    @SerialName("type")
    var type: String? = null

    @SerialName("result_image_url")
    var resultImageUrl: String? = null


    class Builder {
        private var type: String? = null
        private var resultImageUrl: String? = null

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun resultImageUrl(resultImageUrl: String?): Builder {
            this.resultImageUrl = resultImageUrl
            return this
        }

        fun build(): ImageProcessAction {
            val imageProcessAction: ImageProcessAction = com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessAction()
            imageProcessAction.type = type
            imageProcessAction.resultImageUrl = resultImageUrl
            return imageProcessAction
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessAction.Builder()
        }
    }
}