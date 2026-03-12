package com.volcengine.ark.runtime.model.responses.content

import com.fasterxml.jackson.annotation.JsonProperty

class InputContentItemImage : InputContentItem(ResponsesConstants.CONTENT_ITEM_TYPE_INPUT_IMAGE) {
    @JsonProperty("detail")
    var detail: String? = null

    @JsonProperty("image_url")
    var imageUrl: String? = null

    @JsonProperty("file_id")
    var fileId: String? = null

    @JsonProperty("image_pixel_limit")
    private var imagePixelLimit: ImagePixelLimit? = null

    fun getImagePixelLimit(): ImagePixelLimit? {
        return imagePixelLimit
    }

    fun setImagePixelLimit(imagePixelLimit: ImagePixelLimit?) {
        this.imagePixelLimit = imagePixelLimit
    }

    @Override
    fun toString(): String? {
        return "ResponsesContentItemImage{" +
                "type='" + getType() + '\'' +
                ", detail='" + detail + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", fileId='" + fileId + '\'' +
                ", imagePixelLimit=" + imagePixelLimit +
                '}'
    }

    class Builder {
        private var detail: String? = null
        private var imageUrl: String? = null
        private var fileId: String? = null
        private var imagePixelLimit: ImagePixelLimit? = null

        fun detail(detail: String?): Builder {
            this.detail = detail
            return this
        }

        fun imageUrl(imageUrl: String?): Builder {
            this.imageUrl = imageUrl
            return this
        }

        fun fileId(fileId: String?): Builder {
            this.fileId = fileId
            return this
        }

        fun imagePixelLimit(imagePixelLimit: ImagePixelLimit?): Builder {
            this.imagePixelLimit = imagePixelLimit
            return this
        }

        fun build(): InputContentItemImage {
            val responsesContentItemImage: InputContentItemImage = com.volcengine.ark.runtime.model.responses.content.InputContentItemImage()
            responsesContentItemImage.detail = detail
            responsesContentItemImage.imageUrl = imageUrl
            responsesContentItemImage.fileId = fileId
            responsesContentItemImage.setImagePixelLimit(imagePixelLimit)
            return responsesContentItemImage
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.content.InputContentItemImage.Builder()
        }
    }
}