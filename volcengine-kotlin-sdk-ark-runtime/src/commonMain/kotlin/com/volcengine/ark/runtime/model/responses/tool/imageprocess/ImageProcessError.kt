package com.volcengine.ark.runtime.model.responses.tool.imageprocess

import com.fasterxml.jackson.annotation.JsonProperty

class ImageProcessError {
    @JsonProperty("message")
    var message: String? = null

    @Override
    fun toString(): String? {
        return "ImageProcessError{" +
                "message='" + message + '\'' +
                '}'
    }

    class Builder {
        private var message: String? = null

        fun message(message: String?): Builder {
            this.message = message
            return this
        }

        fun build(): ImageProcessError {
            val imageProcessError: ImageProcessError = com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessError()
            imageProcessError.message = message
            return imageProcessError
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessError.Builder()
        }
    }
}