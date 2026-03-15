package com.volcengine.ark.runtime.model.content.generation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CreateContentGenerationTaskRequest(
    @SerialName("model")
    val model: String? = null,

    @SerialName("content")
    val content: List<Content>? = null,

    @SerialName("callback_url")
    val callbackUrl: String? = null,

    @SerialName("return_last_frame")
    val returnLastFrame: Boolean? = null,

    @SerialName("service_tier")
    val serviceTier: String? = null,

    @SerialName("execution_expires_after")
    val executionExpiresAfter: Long? = null,

    @SerialName("generate_audio")
    val generateAudio: Boolean? = null,

    @SerialName("camera_fixed")
    val cameraFixed: Boolean? = null,

    @SerialName("watermark")
    val watermark: Boolean? = null,

    @SerialName("seed")
    val seed: Long? = null,

    @SerialName("resolution")
    val resolution: String? = null,

    @SerialName("ratio")
    val ratio: String? = null,

    @SerialName("duration")
    val duration: Long? = null,

    @SerialName("frames")
    val frames: Long? = null,

    @SerialName("draft")
    val draft: Boolean? = null
) {
    class Builder private constructor() {
        private var model: String? = null
        private var content: List<Content>? = null
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

        fun content(content: List<Content>?): Builder {
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
            return CreateContentGenerationTaskRequest(
                model = model,
                content = content,
                callbackUrl = callbackUrl,
                returnLastFrame = returnLastFrame,
                serviceTier = serviceTier,
                executionExpiresAfter = executionExpiresAfter,
                generateAudio = generateAudio,
                cameraFixed = cameraFixed,
                watermark = watermark,
                seed = seed,
                resolution = resolution,
                ratio = ratio,
                duration = duration,
                frames = frames,
                draft = draft,
            )
        }
    }

    @Serializable
    data class Content(
        @SerialName("type")
        val type: GenerationRequestContentType? = null,

        @SerialName("text")
        val text: String? = null,

        @SerialName("image_url")
        val imageUrl: ImageUrl? = null,

        @SerialName("role")
        val role: ImageRole? = null,

        @SerialName("draft_task")
        val draftTask: DraftTask? = null
    ) {
        companion object {
            fun text(prompt: String): Content {
                return Content(
                    type = GenerationRequestContentType.TEXT,
                    text = prompt,
                )
            }

            fun firstFrame(url: String): Content {
                return Content(
                    type = GenerationRequestContentType.IMAGE_URL,
                    imageUrl = ImageUrl(url),
                    role = ImageRole.FIRST_FRAME,
                )
            }

            fun lastFrame(url: String): Content {
                return Content(
                    type = GenerationRequestContentType.IMAGE_URL,
                    imageUrl = ImageUrl(url),
                    role = ImageRole.LAST_FRAME,
                )
            }

            fun referenceImage(url: String): Content {
                return Content(
                    type = GenerationRequestContentType.IMAGE_URL,
                    imageUrl = ImageUrl(url),
                    role = ImageRole.REFERENCE_IMAGE,
                )
            }

        }

    }
}
