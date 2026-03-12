package com.volcengine.ark.runtime.model.completion.chat

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames


@Serializable
data class ChatCompletionChoice(
    /**
     * This index of this completion in the returned list.
     */
    val index: Int? = null,

    /**
     * The assistant message or delta (when streaming) which was generated
     */
    //@JsonAlias("delta")
    @JsonNames("delta")
    val message: ChatMessage? = null,

    /**
     * The reason why GPT stopped generating, for example "length".
     */
    @SerialName("finish_reason")
    val finishReason: String? = null,

    /**
     * The type of content moderation service hit.
     */
    @SerialName("moderation_hit_type")
    val moderationHitType: String? = null,

    /**
     * Log probability information for the choice.
     */
    @SerialName("logprobs")
    val logprobs: ChatCompletionChoiceLogprob? = null
)
