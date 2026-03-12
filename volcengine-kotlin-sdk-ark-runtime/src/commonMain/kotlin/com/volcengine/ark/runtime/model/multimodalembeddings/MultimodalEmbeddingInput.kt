package com.volcengine.ark.runtime.model.multimodalembeddings

import kotlinx.serialization.Serializable


@Serializable
class MultimodalEmbeddingInput(
    val type: String? = null,

    val text: String? = null,

    val imageUrl: MultiModalEmbeddingContentPartImageURL? = null,

    val videoUrl: MultiModalEmbeddingContentPartVideoURL? = null,
) {
    companion object {
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
                return MultimodalEmbeddingInput(
                    type = type,
                    text = text,
                    imageUrl = imageUrl,
                    videoUrl = videoUrl,
                )
            }
        }

        fun builder(): Builder {
            return Builder()
        }

    }
}
