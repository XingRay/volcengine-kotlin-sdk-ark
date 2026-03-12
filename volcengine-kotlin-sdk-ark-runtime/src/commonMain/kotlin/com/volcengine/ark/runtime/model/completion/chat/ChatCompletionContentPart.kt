package com.volcengine.ark.runtime.model.completion.chat

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ChatCompletionContentPart {
    var type: String? = null

    var content: String? = null

    var text: String? = null

    var imageUrl: ChatCompletionContentPartImageURL? = null

    var videoUrl: ChatCompletionContentPartVideoURL? = null

    @Override
    fun toString(): String? {
        return "ChatCompletionContentPart{" +
                "type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", text='" + text + '\'' +
                ", imageUrl=" + imageUrl +
                ", videoUrl=" + videoUrl +
                '}'
    }

    class ChatCompletionContentPartVideoURL {
        var url: String?
        var fps: Double = 0.0

        @Override
        fun toString(): String? {
            return "ChatCompletionContentPartVideoURL{" +
                    "url='" + url + '\'' +
                    ", fps=" + fps +
                    '}'
        }

        constructor(url: String?) {
            this.url = url
        }

        constructor(url: String?, fps: Double) {
            this.url = url
            this.fps = fps
        }
    }

    class ChatCompletionContentPartImageURL {
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
            return "ChatCompletionContentPartImageURL{" +
                    "url='" + url + '\'' +
                    ", detail='" + detail + '\'' +
                    '}'
        }
    }

    class Builder {
        private var type: String? = null
        private var content: String? = null
        private var text: String? = null
        private var imageUrl: ChatCompletionContentPartImageURL? = null
        private var videoUrl: ChatCompletionContentPartVideoURL? = null

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun content(content: String?): Builder {
            this.content = content
            return this
        }

        fun text(text: String?): Builder {
            this.text = text
            return this
        }

        fun imageUrl(imageUrl: ChatCompletionContentPartImageURL?): Builder {
            this.imageUrl = imageUrl
            return this
        }

        fun videoUrl(videoUrl: ChatCompletionContentPartVideoURL?): Builder {
            this.videoUrl = videoUrl
            return this
        }

        fun build(): ChatCompletionContentPart {
            val chatCompletionContentPart: ChatCompletionContentPart = com.volcengine.ark.runtime.model.completion.chat.ChatCompletionContentPart()
            chatCompletionContentPart.type = type
            chatCompletionContentPart.content = content
            chatCompletionContentPart.text = text
            chatCompletionContentPart.imageUrl = imageUrl
            chatCompletionContentPart.videoUrl = videoUrl
            return chatCompletionContentPart
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.completion.chat.ChatCompletionContentPart.Builder()
        }
    }
}
