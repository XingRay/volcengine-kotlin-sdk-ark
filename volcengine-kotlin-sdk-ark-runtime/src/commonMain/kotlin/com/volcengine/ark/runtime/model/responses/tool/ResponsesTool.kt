package com.volcengine.ark.runtime.model.responses.tool
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(
    [JsonSubTypes.Type(value = ToolFunction::class, name = "function"), JsonSubTypes.Type(
        value = ToolWebSearch::class,
        name = "web_search"
    ), JsonSubTypes.Type(value = ToolImageProcess::class, name = "image_process"), JsonSubTypes.Type(value = ToolMCP::class, name = "mcp"), JsonSubTypes.Type(
        value = ToolDoubaoApp::class,
        name = "doubao_app"
    )]
)
@Serializable
abstract class ResponsesTool {
    @SerialName("type")
    var type: String? = null

    constructor()

    constructor(type: String?) {
        this.type = type
    }
}