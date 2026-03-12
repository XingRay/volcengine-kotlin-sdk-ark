package com.volcengine.ark.runtime.model.responses.content

import com.fasterxml.jackson.annotation.JsonProperty

class ImagePixelLimit {
    @JsonProperty("max_pixels")
    var maxPixels: Long? = null

    @JsonProperty("min_pixels")
    var minPixels: Long? = null

    @Override
    fun toString(): String? {
        return "ImagePixelLimit{" +
                "maxPixels=" + maxPixels +
                ", minPixels=" + minPixels +
                '}'
    }

    class Builder {
        private var maxPixels: Long? = null
        private var minPixels: Long? = null

        fun maxPixels(maxPixels: Long?): Builder {
            this.maxPixels = maxPixels
            return this
        }

        fun minPixels(minPixels: Long?): Builder {
            this.minPixels = minPixels
            return this
        }

        fun build(): ImagePixelLimit {
            val imagePixelLimit: ImagePixelLimit = com.volcengine.ark.runtime.model.responses.content.ImagePixelLimit()
            imagePixelLimit.maxPixels = maxPixels
            imagePixelLimit.minPixels = minPixels
            return imagePixelLimit
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.content.ImagePixelLimit.Builder()
        }
    }
}