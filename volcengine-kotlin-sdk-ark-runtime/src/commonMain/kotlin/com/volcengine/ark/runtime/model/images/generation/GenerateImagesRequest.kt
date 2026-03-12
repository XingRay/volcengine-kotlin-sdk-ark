package com.volcengine.ark.runtime.model.images.generation

import com.fasterxml.jackson.annotation.JsonIgnore

class GenerateImagesRequest {
    @JsonProperty("model")
    var model: String? = null

    @JsonProperty("prompt")
    var prompt: String? = null

    @JsonProperty("image")
    @JsonDeserialize(using = com.volcengine.ark.runtime.model.images.generation.ImageDeserializer::class)
    var image: List<String?>? = null

    @JsonProperty("response_format")
    var responseFormat: String? = null

    @JsonProperty("seed")
    private var seed: Integer? = null

    @JsonProperty("guidance_scale")
    var guidanceScale: Double? = null

    @JsonProperty("size")
    var size: String? = null

    @JsonProperty("watermark")
    var watermark: Boolean? = null

    @JsonProperty("optimize_prompt")
    var optimizePrompt: Boolean? = null

    @JsonProperty("optimize_prompt_options")
    var optimizePromptOptions: OptimizePromptOptions? = null

    @JsonProperty("sequential_image_generation")
    var sequentialImageGeneration: String? = null

    @JsonProperty("sequential_image_generation_options")
    var sequentialImageGenerationOptions: SequentialImageGenerationOptions? = null

    @JsonProperty("stream")
    var stream: Boolean? = null

    @JsonProperty("tools")
    var tools: List<ContentGenerationTool?>? = null

    @JsonProperty("output_format")
    var outputFormat: String? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class ContentGenerationTool {
        @JsonProperty("type")
        var type: String? = null

        @Override
        fun toString(): String? {
            return "ContentGenerationTool{" +
                    "type='" + type + '\'' +
                    '}'
        }

        class Builder {
            private val type: String? = null
        }

        fun build(): ContentGenerationTool {
            val contentGenerationTool: ContentGenerationTool = com.volcengine.ark.runtime.model.images.generation.GenerateImagesRequest.ContentGenerationTool()
            contentGenerationTool.type = type
            return contentGenerationTool
        }

        companion object {
            fun builder(): Builder {
                return com.volcengine.ark.runtime.model.images.generation.GenerateImagesRequest.ContentGenerationTool.Builder()
            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class OptimizePromptOptions {
        var thinking: String? = null

        var mode: String? = null

        @Override
        fun toString(): String? {
            return "OptimizePromptOptions{" +
                    "thinking=" + thinking +
                    "mode=" + mode +
                    '}'
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class SequentialImageGenerationOptions {
        private var maxImages: Integer? = null

        fun getMaxImages(): Integer? {
            return maxImages
        }

        fun setMaxImages(maxImages: Integer?) {
            this.maxImages = maxImages
        }

        @Override
        fun toString(): String? {
            return "SequentialImageGenerationOptions{" +
                    "maxImages=" + maxImages +
                    '}'
        }
    }

    constructor()

    constructor(
        model: String?,
        prompt: String?,
        image: List<String?>?,
        responseFormat: String?,
        seed: Integer?,
        guidanceScale: Double?,
        size: String?,
        watermark: Boolean?,
        sequentialImageGeneration: String?,
        sequentialImageGenerationOptions: SequentialImageGenerationOptions?,
        optimizePrompt: Boolean?,
        optimizePromptOptions: OptimizePromptOptions?,
        stream: Boolean?,
        tools: List<ContentGenerationTool?>?,
        outputFormat: String?
    ) {
        this.model = model
        this.prompt = prompt
        this.image = image
        this.responseFormat = responseFormat
        this.seed = seed
        this.guidanceScale = guidanceScale
        this.size = size
        this.watermark = watermark
        this.optimizePrompt = optimizePrompt
        this.optimizePromptOptions = optimizePromptOptions
        this.sequentialImageGeneration = sequentialImageGeneration
        this.sequentialImageGenerationOptions = sequentialImageGenerationOptions
        this.stream = stream
        this.tools = tools
        this.outputFormat = outputFormat
    }

    @JsonIgnore
    fun setImage(image: String?) {
        this.image = if (image == null) null else Collections.singletonList(image)
    }

    fun getSeed(): Integer? {
        return this.seed
    }

    fun setSeed(seed: Integer?) {
        this.seed = seed
    }

    @Override
    fun toString(): String? {
        return "GenerateImagesRequest{" +
                "model='" + model + '\'' +
                ", prompt=" + prompt +
                ", image=" + image +
                ", responseFormat=" + responseFormat +
                ", seed=" + seed +
                ", guidanceScale=" + guidanceScale +
                ", size=" + size +
                ", watermark=" + watermark +
                ", optimizePrompt=" + optimizePrompt +
                ", optimizePromptOptions=" + optimizePromptOptions +
                ", stream=" + stream +
                ", sequentialImageGeneration=" + sequentialImageGeneration +
                ", sequentialImageGenerationOptions=" + sequentialImageGenerationOptions +
                ", tools=" + tools +
                ", outputFormat=" + outputFormat +
                '}'
    }

    class Builder private constructor() {
        private var model: String? = null
        private var prompt: String? = null

        @JsonDeserialize(using = com.volcengine.ark.runtime.model.images.generation.ImageDeserializer::class)
        private var image: List<String?>? = null
        private var responseFormat: String? = null
        private var seed: Integer? = null
        private var guidanceScale: Double? = null
        private var size: String? = null
        private var watermark: Boolean? = null
        private var optimizePrompt: Boolean? = null
        private var optimizePromptOptions: OptimizePromptOptions? = null
        private var sequentialImageGeneration: String? = null

        private var sequentialImageGenerationOptions: SequentialImageGenerationOptions? = null

        private var tools: List<ContentGenerationTool?>? = null

        private var outputFormat: String? = null

        private var stream: Boolean? = null

        fun model(model: String?): Builder {
            this.model = model
            return this
        }

        fun prompt(prompt: String?): Builder {
            this.prompt = prompt
            return this
        }

        fun image(image: List<String?>?): Builder {
            this.image = image
            return this
        }

        fun image(image: String?): Builder {
            this.image = if (image == null) null else Collections.singletonList(image)
            return this
        }

        fun responseFormat(responseFormat: String?): Builder {
            this.responseFormat = responseFormat
            return this
        }

        fun seed(seed: Integer?): Builder {
            this.seed = seed
            return this
        }

        fun guidanceScale(guidanceScale: Double?): Builder {
            this.guidanceScale = guidanceScale
            return this
        }

        fun size(size: String?): Builder {
            this.size = size
            return this
        }

        fun watermark(watermark: Boolean?): Builder {
            this.watermark = watermark
            return this
        }

        fun optimizePrompt(optimizePrompt: Boolean?): Builder {
            this.optimizePrompt = optimizePrompt
            return this
        }

        fun optimizePromptOptions(optimizePromptOptions: OptimizePromptOptions?): Builder {
            this.optimizePromptOptions = optimizePromptOptions
            return this
        }

        fun sequentialImageGeneration(sequentialImageGeneration: String?): Builder {
            this.sequentialImageGeneration = sequentialImageGeneration
            return this
        }

        fun sequentialImageGenerationOptions(sequentialImageGenerationOptions: SequentialImageGenerationOptions?): Builder {
            this.sequentialImageGenerationOptions = sequentialImageGenerationOptions
            return this
        }

        fun stream(stream: Boolean?): Builder {
            this.stream = stream
            return this
        }

        fun tools(tools: List<ContentGenerationTool?>?): Builder {
            this.tools = tools
            return this
        }

        fun outputFormat(outputFormat: String?): Builder {
            this.outputFormat = outputFormat
            return this
        }

        fun build(): GenerateImagesRequest {
            val generateImagesRequest: GenerateImagesRequest = com.volcengine.ark.runtime.model.images.generation.GenerateImagesRequest()
            generateImagesRequest.model = model
            generateImagesRequest.prompt = prompt
            generateImagesRequest.image = image
            generateImagesRequest.responseFormat = responseFormat
            generateImagesRequest.setSeed(seed)
            generateImagesRequest.guidanceScale = guidanceScale
            generateImagesRequest.size = size
            generateImagesRequest.watermark = watermark
            generateImagesRequest.optimizePrompt = optimizePrompt
            generateImagesRequest.optimizePromptOptions = optimizePromptOptions
            generateImagesRequest.sequentialImageGeneration = sequentialImageGeneration
            generateImagesRequest.sequentialImageGenerationOptions = sequentialImageGenerationOptions
            generateImagesRequest.stream = stream
            generateImagesRequest.tools = tools
            generateImagesRequest.outputFormat = outputFormat
            return generateImagesRequest
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.images.generation.GenerateImagesRequest.Builder()
        }
    }
}

internal class ImageDeserializer : JsonDeserializer<List<String?>?>() {
    @Override
    @Throws(IOException::class, JsonProcessingException::class)
    fun deserialize(p: JsonParser, ctxt: DeserializationContext?): List<String?> {
        val node: JsonNode = p.getCodec().readTree(p)

        // 如果是单个 string
        if (node.isTextual()) {
            return Collections.singletonList(node.asText())
        }

        // 如果是数组
        if (node.isArray()) {
            val list: List<String?> = ArrayList()
            for (element in node) {
                list.add(element.asText())
            }
            return list
        }

        // 其他情况（null、对象等）返回空
        return Collections.emptyList()
    }
}
