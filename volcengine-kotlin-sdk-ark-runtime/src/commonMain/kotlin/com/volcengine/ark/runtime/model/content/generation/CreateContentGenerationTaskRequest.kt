package com.volcengine.ark.runtime.model.content.generation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class CreateContentGenerationTaskRequest {
    @JsonProperty("model")
    var model: String? = null

    @JsonProperty("content")
    var content: List<Content?>? = null

    @JsonProperty("callback_url")
    var callbackUrl: String? = null

    @JsonProperty("return_last_frame")
    var returnLastFrame: Boolean? = null

    @JsonProperty("service_tier")
    var serviceTier: String? = null

    @JsonProperty("execution_expires_after")
    var executionExpiresAfter: Long? = null

    @JsonProperty("generate_audio")
    var generateAudio: Boolean? = null

    @JsonProperty("camera_fixed")
    var cameraFixed: Boolean? = null

    @JsonProperty("watermark")
    var watermark: Boolean? = null

    @JsonProperty("seed")
    var seed: Long? = null

    @JsonProperty("resolution")
    var resolution: String? = null

    @JsonProperty("ratio")
    var ratio: String? = null

    @JsonProperty("duration")
    var duration: Long? = null

    @JsonProperty("frames")
    var frames: Long? = null

    @JsonProperty("draft")
    var draft: Boolean? = null

    constructor()

    constructor(model: String?, content: List<Content?>?) {
        this.model = model
        this.content = content
    }

    constructor(model: String?, content: List<Content?>?, callbackUrl: String?, returnLastFrame: Boolean?) {
        this.model = model
        this.content = content
        this.callbackUrl = callbackUrl
        this.returnLastFrame = returnLastFrame
    }

    constructor(
        model: String?,
        content: List<Content?>?,
        callbackUrl: String?,
        returnLastFrame: Boolean?,
        serviceTier: String?,
        executionExpiresAfter: Long?,
        generateAudio: Boolean?,
        cameraFixed: Boolean?,
        watermark: Boolean?,
        seed: Long?,
        resolution: String?,
        ratio: String?,
        duration: Long?,
        frames: Long?,
        draft: Boolean?
    ) {
        this.model = model
        this.content = content
        this.callbackUrl = callbackUrl
        this.returnLastFrame = returnLastFrame
        this.serviceTier = serviceTier
        this.executionExpiresAfter = executionExpiresAfter
        this.generateAudio = generateAudio
        this.cameraFixed = cameraFixed
        this.watermark = watermark
        this.seed = seed
        this.resolution = resolution
        this.ratio = ratio
        this.duration = duration
        this.frames = frames
        this.draft = draft
    }

    @Override
    fun toString(): String? {
        return "CreateContentGenerationTaskRequest{" +
                "model='" + model + '\'' +
                ", content=" + content +
                ", callbackUrl='" + callbackUrl + '\'' +
                ", returnLastFrame=" + returnLastFrame +
                ", serviceTier='" + serviceTier + '\'' +
                ", executionExpiresAfter=" + executionExpiresAfter +
                ", generateAudio=" + generateAudio +
                ", cameraFixed=" + cameraFixed +
                ", watermark=" + watermark +
                ", seed=" + seed +
                ", resolution='" + resolution + '\'' +
                ", ratio='" + ratio + '\'' +
                ", duration=" + duration +
                ", frames=" + frames +
                ", draft=" + draft +
                '}'
    }

    class Builder private constructor() {
        private var model: String? = null
        private var content: List<Content?>? = null
        private var callbackUrl: String? = null
        private var returnLastFrame: Boolean? = null
        private var serviceTier: String? = null
        private var executionExpiresAfter: Long? = null
        private var generateAudio: Boolean? = null
        private var cameraFixed: Boolean? = null
        private var watermark: Boolean? = null
        private var seed: Long? = null
        private var resolution: String? = null
        private var ratio: String? = null
        private var duration: Long? = null
        private var frames: Long? = null
        private var draft: Boolean? = null

        fun model(model: String?): Builder {
            this.model = model
            return this
        }

        fun content(content: List<Content?>?): Builder {
            this.content = content
            return this
        }

        fun callbackUrl(callbackUrl: String?): Builder {
            this.callbackUrl = callbackUrl
            return this
        }

        fun returnLastFrame(returnLastFrame: Boolean?): Builder {
            this.returnLastFrame = returnLastFrame
            return this
        }

        fun serviceTier(serviceTier: String?): Builder {
            this.serviceTier = serviceTier
            return this
        }

        fun executionExpiresAfter(executionExpiresAfter: Long?): Builder {
            this.executionExpiresAfter = executionExpiresAfter
            return this
        }

        fun generateAudio(generateAudio: Boolean?): Builder {
            this.generateAudio = generateAudio
            return this
        }

        fun cameraFixed(cameraFixed: Boolean?): Builder {
            this.cameraFixed = cameraFixed
            return this
        }

        fun watermark(watermark: Boolean?): Builder {
            this.watermark = watermark
            return this
        }

        fun seed(seed: Long?): Builder {
            this.seed = seed
            return this
        }

        fun resolution(resolution: String?): Builder {
            this.resolution = resolution
            return this
        }

        fun ratio(ratio: String?): Builder {
            this.ratio = ratio
            return this
        }

        fun duration(duration: Long?): Builder {
            this.duration = duration
            return this
        }

        fun frames(frames: Long?): Builder {
            this.frames = frames
            return this
        }

        fun draft(draft: Boolean?): Builder {
            this.draft = draft
            return this
        }

        fun build(): CreateContentGenerationTaskRequest {
            val createContentGenerationTaskRequest: CreateContentGenerationTaskRequest = com.volcengine.ark.runtime.model.content.generation.CreateContentGenerationTaskRequest()
            createContentGenerationTaskRequest.model = model
            createContentGenerationTaskRequest.content = content
            createContentGenerationTaskRequest.callbackUrl = callbackUrl
            createContentGenerationTaskRequest.returnLastFrame = returnLastFrame
            createContentGenerationTaskRequest.serviceTier = serviceTier
            createContentGenerationTaskRequest.executionExpiresAfter = executionExpiresAfter
            createContentGenerationTaskRequest.generateAudio = generateAudio
            createContentGenerationTaskRequest.cameraFixed = cameraFixed
            createContentGenerationTaskRequest.watermark = watermark
            createContentGenerationTaskRequest.seed = seed
            createContentGenerationTaskRequest.resolution = resolution
            createContentGenerationTaskRequest.ratio = ratio
            createContentGenerationTaskRequest.duration = duration
            createContentGenerationTaskRequest.frames = frames
            createContentGenerationTaskRequest.draft = draft
            return createContentGenerationTaskRequest
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Content {
        @JsonProperty("type")
        var type: String? = null

        @JsonProperty("text")
        var text: String? = null

        @JsonProperty("image_url")
        var imageUrl: ImageUrl? = null

        @JsonProperty("role")
        var role: String? = null

        @JsonProperty("draft_task")
        var draftTask: DraftTask? = null

        constructor()

        constructor(type: String?, text: String?, imageUrl: ImageUrl?, role: String?, draftTask: DraftTask?) {
            this.type = type
            this.text = text
            this.imageUrl = imageUrl
            this.role = role
            this.draftTask = draftTask
        }

        @Override
        fun toString(): String? {
            return "Content{" +
                    "type='" + type + '\'' +
                    ", text='" + text + '\'' +
                    ", imageUrl=" + imageUrl +
                    ", role=" + role +
                    ", draftTask=" + draftTask +
                    '}'
        }

        class Builder private constructor() {
            private var type: String? = null
            private var text: String? = null
            private var imageUrl: ImageUrl? = null
            private var role: String? = null
            private var draftTask: DraftTask? = null

            fun type(type: String?): Builder {
                this.type = type
                return this
            }

            fun text(text: String?): Builder {
                this.text = text
                return this
            }

            fun imageUrl(imageUrl: ImageUrl?): Builder {
                this.imageUrl = imageUrl
                return this
            }

            fun role(role: String?): Builder {
                this.role = role
                return this
            }

            fun draftTask(draftTask: DraftTask?): Builder {
                this.draftTask = draftTask
                return this
            }

            fun build(): Content {
                val content: Content = com.volcengine.ark.runtime.model.content.generation.CreateContentGenerationTaskRequest.Content()
                content.type = type
                content.text = text
                content.imageUrl = imageUrl
                content.role = role
                content.draftTask = draftTask
                return content
            }
        }

        companion object {
            fun builder(): Builder {
                return com.volcengine.ark.runtime.model.content.generation.CreateContentGenerationTaskRequest.Content.Builder()
            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class ImageUrl {
        @JsonProperty("url")
        var url: String? = null

        constructor()

        constructor(url: String?) {
            this.url = url
        }

        @Override
        fun toString(): String? {
            return "ImageUrl{" +
                    "url='" + url + '\'' +
                    '}'
        }

        class Builder private constructor() {
            private var url: String? = null

            fun url(url: String?): Builder {
                this.url = url
                return this
            }

            fun build(): ImageUrl {
                val imageUrl: ImageUrl = com.volcengine.ark.runtime.model.content.generation.CreateContentGenerationTaskRequest.ImageUrl()
                imageUrl.url = url
                return imageUrl
            }
        }

        companion object {
            fun builder(): Builder {
                return com.volcengine.ark.runtime.model.content.generation.CreateContentGenerationTaskRequest.ImageUrl.Builder()
            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class DraftTask {
        @JsonProperty("id")
        var id: String? = null

        constructor()

        constructor(id: String?) {
            this.id = id
        }

        @Override
        fun toString(): String? {
            return "DraftTask{" +
                    "id='" + id + '\'' +
                    '}'
        }

        class Builder private constructor() {
            private var id: String? = null

            fun id(id: String?): Builder {
                this.id = id
                return this
            }

            fun build(): DraftTask {
                val draftTask: DraftTask = com.volcengine.ark.runtime.model.content.generation.CreateContentGenerationTaskRequest.DraftTask()
                draftTask.id = id
                return draftTask
            }
        }

        companion object {
            fun builder(): Builder {
                return com.volcengine.ark.runtime.model.content.generation.CreateContentGenerationTaskRequest.DraftTask.Builder()
            }
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.content.generation.CreateContentGenerationTaskRequest.Builder()
        }
    }
}
