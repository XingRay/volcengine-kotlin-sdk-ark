package com.volcengine.ark.runtime.model.responses.tool.imageprocess

import com.fasterxml.jackson.annotation.JsonProperty

class ImageProcessZoomOptions {
    @JsonProperty("type")
    var type: String? = null

    @Override
    fun toString(): String? {
        return "ImageProcessZoomOptions{" +
                "type='" + type + '\'' +
                '}'
    }

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