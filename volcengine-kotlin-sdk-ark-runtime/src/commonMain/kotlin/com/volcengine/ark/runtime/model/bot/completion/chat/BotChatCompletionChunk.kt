package com.volcengine.ark.runtime.model.bot.completion.chat

import com.volcengine.ark.runtime.model.bot.completion.chat.reference.BotChatResultReference
import com.volcengine.ark.runtime.model.bot.completion.chat.usage.BotUsage
import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionChunk
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject


@Serializable
data class BotChatCompletionChunk(
    /**
     * Same as BotChaCompletionRequest.metadata
     */
    val metadata: JsonObject? = null,

    /**
     * In bot chat completion, the bot_usage will be returned instead of usage
     */
    @SerialName("bot_usage")
    val botUsage: BotUsage? = null,

    /**
     * The references returned by Search Actions.
     */
    val references: List<BotChatResultReference>? = null,
) : ChatCompletionChunk()