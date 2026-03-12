package com.volcengine.ark.runtime.model.responses.tool.imageprocess

import com.fasterxml.jackson.annotation.JsonProperty

class ImageProcessAction {
    @JsonProperty("type")
    var type: String? = null

    @JsonProperty("result_image_url")
    var resultImageUrl: String? = null

    @Override
    fun toString(): String? {
        return "ImageProcessAction{" +
                "type='" + type + '\'' +
                ", resultImageUrl='" + resultImageUrl + '\'' +
                '}'
    }

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