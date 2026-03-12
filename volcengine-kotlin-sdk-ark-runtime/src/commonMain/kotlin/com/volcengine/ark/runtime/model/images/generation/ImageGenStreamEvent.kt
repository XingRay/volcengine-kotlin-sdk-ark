package com.volcengine.ark.runtime.model.images.generation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Emitted when streaming image generation events.
 * Contains two situations:
 * - partial image
 * - completed
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class ImageGenStreamEvent {
    // Getters and setters
    /**
     * The type of image generating event.
     */
    var type: String? = null

    /**
     * The model used to generate the images.
     */
    var model: String? = null

    /**
     * The URL of the generated image.
     */
    var url: String? = null

    /**
     * The Base 64 encoded string of the generated image.
     */
    @JsonProperty("b64_json")
    var b64Json: String? = null

    /**
     * The size of the generated image.
     */
    var size: String? = null

    /**
     * The error body, if applicable.
     */
    var error: Error? = null

    /**
     * The usage information for the generation of images.
     */
    var usage: Usage? = null

    /**
     * The index of the image.
     */
    @JsonProperty("image_index")
    var imageIndex: Int = 0

    /**
     * The Unix timestamp when the image was generated.
     */
    @JsonProperty("created")
    var created: Long = 0

    @JsonProperty("tools")
    private var tools: List<GenerateImagesRequest.ContentGenerationTool?>? = null

    fun getTools(): List<GenerateImagesRequest.ContentGenerationTool?>? {
        return this.tools
    }

    fun setTools(tools: List<GenerateImagesRequest.ContentGenerationTool?>?) {
        this.tools = tools
    }

    @Override
    fun toString(): String? {
        return "ImageGenGeneratingEvent{" +
                "type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", url=" + url +
                ", b64Json=" + b64Json +
                ", size=" + size +
                ", error=" + error +
                ", usage=" + usage +
                ", imageIndex=" + imageIndex +
                ", created=" + created +
                ", tools=" + tools +
                '}'
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Usage {
        /**
         * The number of images generated.
         */
        var generatedImages: Int = 0

        /**
         * The number of output tokens.
         */
        var outputTokens: Int = 0

        /**
         * The total number of tokens.
         */
        var totalTokens: Int = 0

        @JsonProperty("tool_usage")
        private var toolUsage: ImagesResponse.Usage.ToolUsage? = null

        fun getToolUsage(): ImagesResponse.Usage.ToolUsage? {
            return toolUsage
        }

        fun setToolUsage(toolUsage: ImagesResponse.Usage.ToolUsage?) {
            this.toolUsage = toolUsage
        }

        @Override
        fun toString(): String? {
            return "Usage{" +
                    "generatedImages=" + generatedImages +
                    "outputTokens=" + outputTokens +
                    "totalTokens=" + totalTokens +
                    "toolUsage=" + toolUsage +
                    '}'
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Error {
        /**
         * The reason for failed image generation.
         */
        var message: String? = null

        /**
         * The error code for failed image generation.
         */
        var code: String? = null

        @Override
        fun toString(): String? {
            return "Error{" +
                    "message='" + message + '\'' +
                    ", code='" + code + '\'' +
                    '}'
        }
    }
}

