package com.volcengine.ark.runtime.model.images.generation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ImagesResponse(
    @SerialName("model")
    val model: String? = null,

    @SerialName("created")
    val created: Int? = null,

    @SerialName("data")
    val data: List<Image?>? = null,

    @SerialName("usage")
    val usage: Usage? = null,

    @SerialName("error")
    val error: Error? = null,

    @SerialName("tools")
    val tools: List<ContentGenerationTool>? = null
) {
    companion object {
        class Builder {
            private var model: String? = null
            private var created: Int? = null
            private var data: List<Image?>? = null
            private var usage: Usage? = null
            private var error: Error? = null
            private var tools: List<ContentGenerationTool>? = null

            fun model(model: String?): Builder {
                this.model = model
                return this
            }

            fun created(created: Int?): Builder {
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

            fun tools(tools: List<ContentGenerationTool>?): Builder {
                this.tools = tools
                return this
            }

            fun build(): ImagesResponse {
                return ImagesResponse(
                    model = model,
                    created = created,
                    data = data,
                    usage = usage,
                    error = error,
                    tools = tools,
                )
            }
        }
    }

}
