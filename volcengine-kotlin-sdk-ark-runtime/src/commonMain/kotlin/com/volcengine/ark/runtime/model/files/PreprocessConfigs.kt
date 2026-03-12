package com.volcengine.ark.runtime.model.files

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class PreprocessConfigs {
    @JsonProperty(value = "video")
    private var video: Video? = null

    fun getVideo(): Video? {
        return video
    }

    fun setVideo(video: Video?) {
        this.video = video
    }

    class PreprocessConfigsBuilder private constructor() {
        private var video: Video? = null

        fun video(video: Video?): PreprocessConfigsBuilder {
            this.video = video
            return this
        }

        fun build(): PreprocessConfigs {
            val preprocessConfigs: PreprocessConfigs = com.volcengine.ark.runtime.model.files.PreprocessConfigs()
            preprocessConfigs.setVideo(video)
            return preprocessConfigs
        }

        companion object {
            fun aPreprocessConfigs(): PreprocessConfigsBuilder {
                return com.volcengine.ark.runtime.model.files.PreprocessConfigs.PreprocessConfigsBuilder()
            }
        }
    }

    companion object {
        fun builder(): PreprocessConfigsBuilder {
            return com.volcengine.ark.runtime.model.files.PreprocessConfigs.PreprocessConfigsBuilder()
        }
    }
}
