package com.volcengine.ark.runtime.model.bot.completion.chat.usage

import com.volcengine.ark.runtime.model.Usage
import kotlinx.serialization.Serializable


@Serializable
data class BotModelUsage(
    /**
     * The model name
     */
    val name: String? = null
) : Usage() {


}
