package com.volcengine.ark.runtime.model.bot.completion.chat.usage

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class BotUsage {
    /**
     * The model usages
     */
    @JsonProperty("model_usage")
    private var modelUsage: List<BotModelUsage?>? = null

    /**
     * The action usages
     */
    @JsonProperty("action_usage")
    private var actionUsage: List<BotActionUsage?>? = null

    fun getModelUsage(): List<BotModelUsage?>? {
        return modelUsage
    }

    fun setModelUsage(modelUsage: List<BotModelUsage?>?) {
        this.modelUsage = modelUsage
    }

    fun getActionUsage(): List<BotActionUsage?>? {
        return actionUsage
    }

    fun setActionUsage(actionUsage: List<BotActionUsage?>?) {
        this.actionUsage = actionUsage
    }
}
