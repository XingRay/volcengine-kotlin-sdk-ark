package com.volcengine.ark.runtime.model.multimodalembeddings

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class MultimodalEmbeddingInput {
    var type: String? = null

    var text: String? = null

    var imageUrl: MultiModalEmbeddingContentPartImageURL? = null

    var videoUrl: MultiModalEmbeddingContentPartVideoURL? = null

    @Override
    fun toString(): String? {
        return "MultiModalEmbeddingInput{" +
                "type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", imageUrl=" + imageUrl +
                ", videoUrl=" + videoUrl +
                '}'
    }

    class MultiModalEmbeddingContentPartImageURL {
        var url: String? = null

        var detail: String? = null

        constructor(url: String?, detail: String?) {
            this.url = url
            this.detail = detail
        }

        constructor(url: String?) {
            this.url = url
        }

        constructor()

        @Override
        fun toString(): String? {
            return "MultiModalEmbeddingContentPartImageURL{" +
                    "url='" + url + '\'' +
                    ", detail='" + detail + '\'' +
                    '}'
        }
    }

    class MultiModalEmbeddingContentPartVideoURL {
        var url: String? = null
        var fps: Double = 0.0

        constructor(url: String?, fps: Double) {
            this.url = url
            this.fps = fps
        }

        constructor(url: String?) {
            this.url = url
        }

        constructor()

        @Override
        fun toString(): String? {
            return "MultiModalEmbeddingContentPartVideoURL{" +
                    "url='" + url + '\'' +
                    ", fps=" + fps +
                    '}'
        }
    }

    class Builder {
        private var type: String? = null
        private var text: String? = null
        private var imageUrl: MultiModalEmbeddingContentPartImageURL? = null
        private var videoUrl: MultiModalEmbeddingContentPartVideoURL? = null

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun text(text: String?): Builder {
            this.text = text
            return this
        }

        fun imageUrl(imageUrl: MultiModalEmbeddingContentPartImageURL?): Builder {
            this.imageUrl = imageUrl
            return this
        }

        fun videoUrl(videoUrl: MultiModalEmbeddingContentPartVideoURL?): Builder {
            this.videoUrl = videoUrl
            return this
        }

        fun build(): MultimodalEmbeddingInput {
            val multiModalEmbeddingInput: MultimodalEmbeddingInput = com.volcengine.ark.runtime.model.multimodalembeddings.MultimodalEmbeddingInput()
            multiModalEmbeddingInput.type = type
            multiModalEmbeddingInput.text = text
            multiModalEmbeddingInput.imageUrl = imageUrl
            multiModalEmbeddingInput.videoUrl = videoUrl
            return multiModalEmbeddingInput
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.multimodalembeddings.MultimodalEmbeddingInput.Builder()
        }
    }
}
