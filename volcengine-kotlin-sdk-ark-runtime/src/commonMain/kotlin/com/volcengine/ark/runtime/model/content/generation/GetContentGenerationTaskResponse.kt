package com.volcengine.ark.runtime.model.content.generation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class GetContentGenerationTaskResponse {
    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("model")
    var model: String? = null

    @JsonProperty("status")
    var status: String? = null

    @JsonProperty("error")
    var error: ContentGenerationError? = null

    @JsonProperty("content")
    var content: Content? = null

    @JsonProperty("usage")
    var usage: Usage? = null

    @JsonProperty("subdivisionlevel")
    var subdivisionLevel: String? = null

    @JsonProperty("fileformat")
    var fileFormat: String? = null

    @JsonProperty("frames")
    var frames: Long? = null

    @JsonProperty("framespersecond")
    var framesPerSecond: Long? = null

    @JsonProperty("created_at")
    var createdAt: Long? = null

    @JsonProperty("updated_at")
    var updatedAt: Long? = null

    @JsonProperty("seed")
    var seed: Long? = null

    @JsonProperty("revised_prompt")
    var revisedPrompt: String? = null

    @JsonProperty("service_tier")
    var serviceTier: String? = null

    @JsonProperty("execution_expires_after")
    var executionExpiresAfter: Long? = null

    @JsonProperty("generate_audio")
    var generateAudio: Boolean? = null

    @JsonProperty("ratio")
    var ratio: String? = null

    @JsonProperty("duration")
    var duration: Long? = null

    @JsonProperty("resolution")
    var resolution: String? = null

    @JsonProperty("draft")
    var draft: Boolean? = null

    @JsonProperty("draft_task_id")
    var draftTaskID: String? = null


    @JsonIgnoreProperties(ignoreUnknown = true)
    class Content {
        @JsonProperty("video_url")
        var videoUrl: String? = null

        @JsonProperty("last_frame_url")
        var lastFrameUrl: String? = null

        @JsonProperty("file_url")
        var fileUrl: String? = null

        @Override
        fun toString(): String? {
            return "Content{" +
                    "videoUrl='" + videoUrl + '\'' +
                    ", lastFrameUrl='" + lastFrameUrl + '\'' +
                    ", fileUrl='" + fileUrl + '\'' +
                    '}'
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Usage {
        @JsonProperty("completion_tokens")
        var completionTokens: Int = 0

        @Override
        fun toString(): String? {
            return "Usage{" +
                    "completionTokens=" + completionTokens +
                    '}'
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class ContentGenerationError {
        @JsonProperty("message")
        var message: String? = null

        @JsonProperty("code")
        var code: String? = null

        @Override
        fun toString(): String? {
            return "Error{" +
                    "message='" + message + '\'' +
                    ", code='" + code + '\'' +
                    "}"
        }
    }

    @Override
    fun toString(): String? {
        return "GetContentGenerationTaskResponse{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", status='" + status + '\'' +
                ", error='" + error + '\'' +
                ", content=" + (if (content != null) content.toString() else "null") +
                ", usage=" + usage +
                ", subdivisionlevel=" + this.subdivisionLevel +
                ", fileformat=" + this.fileFormat +
                ", frames=" + frames +
                ", framespersecond=" + this.framesPerSecond +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", seed=" + seed +
                ", revisedPrompt=" + revisedPrompt +
                ", serviceTier='" + serviceTier + '\'' +
                ", executionExpiresAfter=" + executionExpiresAfter +
                ", generateAudio=" + generateAudio +
                ", ratio='" + ratio + '\'' +
                ", duration=" + duration +
                ", resolution='" + resolution + '\'' +
                ", draft=" + draft +
                ", draftTaskID='" + draftTaskID + '\'' +
                '}'
    }
}
