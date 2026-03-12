package com.volcengine.ark.runtime.model.bot.completion.chat.usage

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class BotModelUsage : Usage() {
    /**
     * The model name
     */
    var name: String? = null

    @Override
    fun toString(): String? {
        return "BotModelUsage{" +
                "promptTokens=" + getPromptTokens() +
                ", completionTokens=" + getCompletionTokens() +
                ", totalTokens=" + getTotalTokens() +
                ", name='" + name + '\'' +
                '}'
    }
}
