package com.volcengine.ark.runtime.model.bot.completion.chat.usage
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class BotActionUsage(
    /**
     * For Search Action: The source type name
     */
    val name: String? = null,

    @SerialName("prompt_tokens")
    val promptTokens: String? = null,

    @SerialName("completion_tokens")
    val completionTokens: Int? = null,

    @SerialName("total_tokens")
    val totalTokens: Int? = null,

    /**
     * For Search Action: return the search count from this source type
     */
    @SerialName("search_count")
    val searchCount: Int? = null,

    @SerialName("action_name")
    val actionName: String? = null,

    @SerialName("count")
    val count: Int? = null
)
