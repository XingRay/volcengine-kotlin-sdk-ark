package com.volcengine.ark.runtime.model.responses.usage
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonElement


@Serializable
data class Usage(
    @SerialName("input_tokens")
    val inputTokens: Long? = null,

    @SerialName("output_tokens")
    val outputTokens: Long? = null,

    @SerialName("total_tokens")
    val totalTokens: Long? = null,

    @SerialName("input_tokens_details")
    val inputTokensDetails: InputTokensDetails? = null,

    @SerialName("output_tokens_details")
    val outputTokensDetails: OutputTokensDetails? = null,

    @SerialName("tool_usage")
    val toolUsage: Map<String, JsonElement>? = null,

    @SerialName("tool_usage_details")
    val toolUsageDetails: Map<String, JsonElement>? = null
)