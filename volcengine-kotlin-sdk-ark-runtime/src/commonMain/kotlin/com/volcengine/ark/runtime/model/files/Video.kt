package com.volcengine.ark.runtime.model.files


import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Video {
    constructor(fps: Double?) {
        this.fps = fps
    }

    @JsonProperty(value = "fps")
    var fps: Double? = null

    constructor()

    class VideoBuilder private constructor() {
        private var fps: Double? = null

        fun fps(fps: Double?): VideoBuilder {
            this.fps = fps
            return this
        }

        fun build(): Video {
            val video = Video()
            video.fps = fps
            return video
        }

        companion object {
            fun aVideo(): VideoBuilder {
                return com.volcengine.ark.runtime.model.files.Video.VideoBuilder()
            }
        }
    }
}
