package com.volcengine.ark.runtime.model.responses.tool.imageprocess

import com.fasterxml.jackson.annotation.JsonProperty

class ImageProcessPointOptions {
    @JsonProperty("type")
    var type: String? = null

    @Override
    fun toString(): String? {
        return "ImageProcessPointOptions{" +
                "type='" + type + '\'' +
                '}'
    }

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