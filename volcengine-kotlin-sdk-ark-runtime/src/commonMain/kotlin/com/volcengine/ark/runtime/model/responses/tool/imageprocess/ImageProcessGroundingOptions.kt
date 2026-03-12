package com.volcengine.ark.runtime.model.responses.tool.imageprocess

import com.fasterxml.jackson.annotation.JsonProperty

class ImageProcessGroundingOptions {
    @JsonProperty("type")
    var type: String? = null

    @Override
    fun toString(): String? {
        return "ImageProcessGroundingOptions{" +
                "type='" + type + '\'' +
                '}'
    }

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