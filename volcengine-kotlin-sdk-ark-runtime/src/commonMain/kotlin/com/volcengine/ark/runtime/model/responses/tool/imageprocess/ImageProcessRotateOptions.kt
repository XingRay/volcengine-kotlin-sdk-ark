package com.volcengine.ark.runtime.model.responses.tool.imageprocess

import com.fasterxml.jackson.annotation.JsonProperty

class ImageProcessRotateOptions {
    @JsonProperty("type")
    var type: String? = null

    @Override
    fun toString(): String? {
        return "ImageProcessRotateOptions{" +
                "type='" + type + '\'' +
                '}'
    }

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