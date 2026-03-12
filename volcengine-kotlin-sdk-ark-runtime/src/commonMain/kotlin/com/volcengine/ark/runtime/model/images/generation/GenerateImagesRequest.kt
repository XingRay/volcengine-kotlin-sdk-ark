package com.volcengine.ark.runtime.model.images.generation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// ===================== 主类：GenerateImagesRequest =====================
@Serializable
data class GenerateImagesRequest(
    @SerialName("model")
    var model: String? = null,

    @SerialName("prompt")
    var prompt: String? = null,

    @SerialName("image")
    @Serializable(with = ImageListSerializer::class) // 替换自定义反序列化器
    var image: List<String>? = null,

    @SerialName("response_format")
    var responseFormat: String? = null,

    @SerialName("seed")
    var seed: Int? = null,

    @SerialName("guidance_scale")
    var guidanceScale: Double? = null,

    @SerialName("size")
    var size: String? = null,

    @SerialName("watermark")
    var watermark: Boolean? = null,

    @SerialName("optimize_prompt")
    var optimizePrompt: Boolean? = null,

    @SerialName("optimize_prompt_options")
    var optimizePromptOptions: OptimizePromptOptions? = null,

    @SerialName("sequential_image_generation")
    var sequentialImageGeneration: String? = null,

    @SerialName("sequential_image_generation_options")
    var sequentialImageGenerationOptions: SequentialImageGenerationOptions? = null,

    @SerialName("stream")
    var stream: Boolean? = null,

    @SerialName("tools")
    var tools: List<ContentGenerationTool>? = null,

    @SerialName("output_format")
    var outputFormat: String? = null
) {

    companion object {
        class Builder {
            private var model: String? = null
            private var prompt: String? = null
            private var image: List<String>? = null
            private var responseFormat: String? = null
            private var seed: Int? = null
            private var guidanceScale: Double? = null
            private var size: String? = null
            private var watermark: Boolean? = null
            private var optimizePrompt: Boolean? = null
            private var optimizePromptOptions: OptimizePromptOptions? = null
            private var sequentialImageGeneration: String? = null
            private var sequentialImageGenerationOptions: SequentialImageGenerationOptions? = null
            private var stream: Boolean? = null
            private var tools: List<ContentGenerationTool>? = null
            private var outputFormat: String? = null

            fun model(model: String?) = apply { this.model = model }
            fun prompt(prompt: String?) = apply { this.prompt = prompt }

            // 重载 image 方法：支持 List<String>
            fun image(image: List<String>?) = apply { this.image = image }

            // 重载 image 方法：支持单个 String
            fun image(image: String?) = apply { this.image = image?.let { listOf(it) } }

            fun responseFormat(responseFormat: String?) = apply { this.responseFormat = responseFormat }
            fun seed(seed: Int?) = apply { this.seed = seed }
            fun guidanceScale(guidanceScale: Double?) = apply { this.guidanceScale = guidanceScale }
            fun size(size: String?) = apply { this.size = size }
            fun watermark(watermark: Boolean?) = apply { this.watermark = watermark }
            fun optimizePrompt(optimizePrompt: Boolean?) = apply { this.optimizePrompt = optimizePrompt }
            fun optimizePromptOptions(optimizePromptOptions: OptimizePromptOptions?) = apply {
                this.optimizePromptOptions = optimizePromptOptions
            }

            fun sequentialImageGeneration(sequentialImageGeneration: String?) = apply {
                this.sequentialImageGeneration = sequentialImageGeneration
            }

            fun sequentialImageGenerationOptions(sequentialImageGenerationOptions: SequentialImageGenerationOptions?) = apply {
                this.sequentialImageGenerationOptions = sequentialImageGenerationOptions
            }

            fun stream(stream: Boolean?) = apply { this.stream = stream }
            fun tools(tools: List<ContentGenerationTool>?) = apply { this.tools = tools }
            fun outputFormat(outputFormat: String?) = apply { this.outputFormat = outputFormat }

            fun build(): GenerateImagesRequest {
                return GenerateImagesRequest(
                    model = model,
                    prompt = prompt,
                    image = image,
                    responseFormat = responseFormat,
                    seed = seed,
                    guidanceScale = guidanceScale,
                    size = size,
                    watermark = watermark,
                    optimizePrompt = optimizePrompt,
                    optimizePromptOptions = optimizePromptOptions,
                    sequentialImageGeneration = sequentialImageGeneration,
                    sequentialImageGenerationOptions = sequentialImageGenerationOptions,
                    stream = stream,
                    tools = tools,
                    outputFormat = outputFormat
                )
            }
        }

        fun builder() = Builder()
    }
}
