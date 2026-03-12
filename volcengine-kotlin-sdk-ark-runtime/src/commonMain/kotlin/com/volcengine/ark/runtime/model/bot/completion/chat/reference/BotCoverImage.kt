package com.volcengine.ark.runtime.model.bot.completion.chat.reference
import kotlinx.serialization.Serializable


@Serializable
data class BotCoverImage(
    val url: String? = null,

    val width: Int? = null,

    val height: Int? = null
)
