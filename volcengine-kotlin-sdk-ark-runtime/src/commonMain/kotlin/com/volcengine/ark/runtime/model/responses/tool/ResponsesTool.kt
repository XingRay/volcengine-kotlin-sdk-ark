package com.volcengine.ark.runtime.model.responses.tool
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
sealed class ResponsesTool {
    @SerialName("type")
    abstract val type: String?
}