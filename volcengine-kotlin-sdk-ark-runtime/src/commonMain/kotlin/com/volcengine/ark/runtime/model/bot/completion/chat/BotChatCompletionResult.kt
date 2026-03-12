package com.volcengine.ark.runtime.model.bot.completion.chat

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class BotChatCompletionResult : ChatCompletionResult() {
    /**
     * Same as BotChaCompletionRequest.metadata
     */
    private var metadata: Map<String?, Object?>? = null

    /**
     * In bot chat completion, the bot_usage will be returned instead of usage
     */
    @JsonProperty("bot_usage")
    private var botUsage: BotUsage? = null

    /**
     * The references returned by Search Actions.
     */
    private var references: List<BotChatResultReference?>? = null

    fun getMetadata(): Map<String?, Object?>? {
        return metadata
    }

    fun setMetadata(metadata: Map<String?, Object?>?) {
        this.metadata = metadata
    }

    fun getBotUsage(): BotUsage? {
        return botUsage
    }

    fun setBotUsage(botUsage: BotUsage?) {
        this.botUsage = botUsage
    }

    fun getReferences(): List<BotChatResultReference?>? {
        return references
    }

    fun setReferences(references: List<BotChatResultReference?>?) {
        this.references = references
    }
}
