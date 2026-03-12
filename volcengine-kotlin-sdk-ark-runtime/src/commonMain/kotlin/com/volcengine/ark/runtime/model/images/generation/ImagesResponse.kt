package com.volcengine.ark.runtime.model.images.generation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

class ImagesResponse {
    @JsonProperty("model")
    var model: String? = null

    @JsonProperty("created")
    private var created: Integer? = null

    @JsonProperty("data")
    var data: List<Image?>? = null

    @JsonProperty("usage")
    var usage: Usage? = null

    @JsonProperty("error")
    var error: Error? = null

    @JsonProperty("tools")
    private var tools: List<GenerateImagesRequest.ContentGenerationTool?>? = null

    fun getCreated(): Integer? {
        return created
    }

    fun setCreated(created: Integer?) {
        this.created = created
    }

    fun getTools(): List<GenerateImagesRequest.ContentGenerationTool?>? {
        return this.tools
    }

    fun setTools(tools: List<GenerateImagesRequest.ContentGenerationTool?>?) {
        this.tools = tools
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Image {
        @JsonProperty("url")
        var url: String? = null

        @JsonProperty("b64_json")
        var b64Json: String? = null

        @JsonProperty("size")
        var size: String? = null

        @Override
        fun toString(): String? {
            return "Image{" +
                    "url=" + url +
                    "b64Json=" + b64Json +
                    "size=" + size +
                    '}'
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Usage {
        @JsonIgnoreProperties(ignoreUnknown = true)
        class ToolUsage {
            @JsonProperty("web_search")
            private var webSearch: Integer? = null

            fun getWebSearch(): Integer? {
                return webSearch
            }

            fun setWebSearch(webSearch: Integer?) {
                this.webSearch = webSearch
            }

            @Override
            fun toString(): String? {
                return "ToolUsage{" +
                        "webSearch=" + webSearch +
                        '}'
            }
        }

        @JsonProperty("generated_images")
        private var generatedImages: Integer? = null

        fun getGeneratedImages(): Integer? {
            return generatedImages
        }

        fun setGeneratedImages(generatedImages: Integer?) {
            this.generatedImages = generatedImages
        }

        @JsonProperty("output_tokens")
        private var outputTokens: Integer? = null

        fun getOutputTokens(): Integer? {
            return outputTokens
        }

        fun setOutputTokens(outputTokens: Integer?) {
            this.outputTokens = outputTokens
        }

        @JsonProperty("total_tokens")
        private var totalTokens: Integer? = null

        fun getTotalTokens(): Integer? {
            return totalTokens
        }

        fun setTotalTokens(totalTokens: Integer?) {
            this.totalTokens = totalTokens
        }

        @JsonProperty("tool_usage")
        var toolUsage: ToolUsage? = null

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
        @JsonProperty("code")
        var code: String? = null

        @JsonProperty("message")
        var message: String? = null

        @Override
        fun toString(): String? {
            return "Error{" +
                    "code=" + code +
                    "message=" + message +
                    '}'
        }
    }

    @Override
    fun toString(): String? {
        return "ImagesResponse{" +
                "model='" + model + '\'' +
                "created=" + created +
                "data=" + data +
                "usage=" + usage +
                "error=" + error +
                "tools=" + tools +
                '}'
    }

    class Builder private constructor() {
        private var model: String? = null
        private var created: Integer? = null
        private var data: List<Image?>? = null
        private var usage: Usage? = null
        private var error: Error? = null
        private var tools: List<GenerateImagesRequest.ContentGenerationTool?>? = null

        fun model(model: String?): Builder {
            this.model = model
            return this
        }

        fun created(created: Integer?): Builder {
            this.created = created
            return this
        }

        fun data(data: List<Image?>?): Builder {
            this.data = data
            return this
        }

        fun usage(usage: Usage?): Builder {
            this.usage = usage
            return this
        }

        fun error(error: Error?): Builder {
            this.error = error
            return this
        }

        fun tools(tools: List<GenerateImagesRequest.ContentGenerationTool?>?): Builder {
            this.tools = tools
            return this
        }

        fun build(): ImagesResponse {
            val imagesResponse: ImagesResponse = com.volcengine.ark.runtime.model.images.generation.ImagesResponse()
            imagesResponse.model = model
            imagesResponse.setCreated(created)
            imagesResponse.data = data
            imagesResponse.usage = usage
            imagesResponse.error = error
            imagesResponse.setTools(tools)
            return imagesResponse
        }
    }
}
