package com.volcengine.ark.runtime.model.responses.content

import com.fasterxml.jackson.annotation.JsonProperty

class InputContentItemVideo : InputContentItem(ResponsesConstants.CONTENT_ITEM_TYPE_INPUT_VIDEO) {
    @JsonProperty("video_url")
    var videoUrl: String? = null

    @JsonProperty("file_id")
    var fileId: String? = null

    @JsonProperty("fps")
    var fps: Float? = null

    @Override
    fun toString(): String? {
        return "InputContentItemVideo{" +
                "type='" + getType() + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", fileId='" + fileId + '\'' +
                ", fps=" + fps +
                '}'
    }

    class Builder {
        private var videoUrl: String? = null
        private var fileId: String? = null
        private var fps: Float? = null

        fun type(type: String?): Builder {
            return this
        }

        fun videoUrl(videoUrl: String?): Builder {
            this.videoUrl = videoUrl
            return this
        }

        fun fileId(fileId: String?): Builder {
            this.fileId = fileId
            return this
        }

        fun fps(fps: Float?): Builder {
            this.fps = fps
            return this
        }

        fun build(): InputContentItemVideo {
            val responsesContentItemVideo: InputContentItemVideo = com.volcengine.ark.runtime.model.responses.content.InputContentItemVideo()
            responsesContentItemVideo.videoUrl = videoUrl
            responsesContentItemVideo.fileId = fileId
            responsesContentItemVideo.fps = fps
            return responsesContentItemVideo
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.content.InputContentItemVideo.Builder()
        }
    }
}