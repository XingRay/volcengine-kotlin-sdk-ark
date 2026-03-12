package com.volcengine.ark.runtime.model.bot.completion.chat.usage
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class BotUsage(
    /**
     * The model usages
     */
    @SerialName("model_usage")
    val modelUsage: List<BotModelUsage?>? = null,

    /**
     * The action usages
     */
    @SerialName("action_usage")
    val actionUsage: List<BotActionUsage?>? = null
)
