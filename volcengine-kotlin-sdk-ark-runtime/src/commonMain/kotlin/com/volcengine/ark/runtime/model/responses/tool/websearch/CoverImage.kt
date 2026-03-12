package com.volcengine.ark.runtime.model.responses.tool.websearch

import com.fasterxml.jackson.annotation.JsonProperty

class CoverImage {
    @JsonProperty("url")
    var url: String? = null

    @JsonProperty("width")
    var width: Long? = null

    @JsonProperty("height")
    var height: Long? = null

    @Override
    fun toString(): String? {
        return "CoverImage{" +
                "url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}'
    }

    class Builder {
        private var url: String? = null
        private var width: Long? = null
        private var height: Long? = null

        fun url(url: String?): Builder {
            this.url = url
            return this
        }

        fun width(width: Long?): Builder {
            this.width = width
            return this
        }

        fun height(height: Long?): Builder {
            this.height = height
            return this
        }

        fun build(): CoverImage {
            val coverImage: CoverImage = com.volcengine.ark.runtime.model.responses.tool.websearch.CoverImage()
            coverImage.url = url
            coverImage.width = width
            coverImage.height = height
            return coverImage
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.websearch.CoverImage.Builder()
        }
    }
}