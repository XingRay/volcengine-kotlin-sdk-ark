package com.volcengine.ark.runtime.model.bot.completion.chat

import com.volcengine.ark.runtime.model.Usage
import com.volcengine.ark.runtime.model.bot.completion.chat.reference.BotChatResultReference
import com.volcengine.ark.runtime.model.bot.completion.chat.usage.BotUsage
import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionChoice
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject


@Serializable
class BotChatCompletionResult(
    /**
     * Unique id assigned to this chat completion.
     */
    val id: String? = null,

    /**
     * The type of object returned, should be "chat.completion"
     */
    val `object`: String? = null,

    /**
     * The creation time in epoch seconds.
     */
    val created: Long = 0,

    /**
     * The GPT model used.
     */
    val model: String? = null,

    /**
     * Specifies the latency tier to use for processing the request.
     *
     * This parameter is relevant for customers subscribed to the scale tier service:
     *
     * - If set to 'auto', and the endpoint is Scale tier enabled, the system will
     * utilize scale tier credits until they are exhausted.
     * - If set to 'auto', and the endpoint is not Scale tier enabled, the request will
     * be processed using the default service tier with a lower uptime SLA and no
     * latency guarentee.
     * - If set to 'default', the request will be processed using the default service
     * tier with a lower uptime SLA and no latency guarentee.
     * - When not set, the default behavior is 'auto'.
     *
     * When this parameter is set, the response body will include the `service_tier`
     * utilized.
     */
    @SerialName("service_tier")
    val serviceTier: String? = null,

    /**
     * A list of all generated completions.
     */
    val choices: List<ChatCompletionChoice>? = null,

    /**
     * The API usage for this request.
     */
    val usage: Usage? = null,

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
    val references: List<BotChatResultReference?>? = null

) {

}
