package com.volcengine.ark.runtime.model.responses.tool.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class DoubaoAppFeature(
    @SerialName("chat")
    val chat: DoubaoAppFeatureItem? = null,

    @SerialName("deep_chat")
    val deepChat: DoubaoAppFeatureItem? = null,

    @SerialName("ai_search")
    val aiSearch: DoubaoAppFeatureItem? = null,

    @SerialName("reasoning_search")
    val reasoningSearch: DoubaoAppFeatureItem? = null
)
