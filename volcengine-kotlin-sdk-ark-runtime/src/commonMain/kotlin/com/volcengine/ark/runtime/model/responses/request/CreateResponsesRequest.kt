package com.volcengine.ark.runtime.model.responses.request

import com.volcengine.ark.runtime.model.responses.common.ResponsesCaching
import com.volcengine.ark.runtime.model.responses.common.ResponsesReasoning
import com.volcengine.ark.runtime.model.responses.common.ResponsesText
import com.volcengine.ark.runtime.model.responses.common.ResponsesThinking
import com.volcengine.ark.runtime.model.responses.tool.ResponsesTool
import com.volcengine.ark.runtime.model.responses.tool.ResponsesToolChoice
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateResponsesRequest(
    @SerialName("input")
    val input: ResponsesInput? = null,

    @SerialName("model")
    val model: String? = null,

    @SerialName("max_output_tokens")
    val maxOutputTokens: Long? = null,

    @SerialName("previous_response_id")
    val previousResponseId: String? = null,

    @SerialName("thinking")
    val thinking: ResponsesThinking? = null,

    @SerialName("reasoning")
    val reasoning: ResponsesReasoning? = null,

    @SerialName("service_tier")
    val serviceTier: String? = null,

    @SerialName("store")
    val store: Boolean? = null,

    @SerialName("stream")
    val stream: Boolean? = null,

    @SerialName("temperature")
    val temperature: Double? = null,

    @SerialName("tools")
    val tools: List<ResponsesTool?>? = null,

    @SerialName("top_p")
    val topP: Double? = null,

    @SerialName("instructions")
    val instructions: String? = null,

    @SerialName("include")
    val include: List<String?>? = null,

    @SerialName("caching")
    val caching: ResponsesCaching? = null,

    @SerialName("text")
    val text: ResponsesText? = null,

    @SerialName("expire_at")
    val expireAt: Long? = null,

    @SerialName("tool_choice")
    val toolChoice: ResponsesToolChoice? = null,

    @SerialName("parallel_tool_calls")
    val parallelToolCalls: Boolean? = null,

    @SerialName("max_tool_calls")
    val maxToolCalls: Long? = null
)