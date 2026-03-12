package com.volcengine.ark.runtime.model.responses.tool
import kotlinx.serialization.Serializable

@Serializable
data class ResponsesToolChoice(
    val mode: String? = null,
    val functionToolChoice: FunctionToolChoice? = null
)