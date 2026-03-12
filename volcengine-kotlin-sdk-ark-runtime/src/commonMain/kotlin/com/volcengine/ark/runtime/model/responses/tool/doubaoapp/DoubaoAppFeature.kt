package com.volcengine.ark.runtime.model.responses.tool.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class DoubaoAppFeature {
    @SerialName("chat")
    private var chat: DoubaoAppFeatureItem? = null

    @SerialName("deep_chat")
    private var deepChat: DoubaoAppFeatureItem? = null

    @SerialName("ai_search")
    private var aiSearch: DoubaoAppFeatureItem? = null

    @SerialName("reasoning_search")
    private var reasoningSearch: DoubaoAppFeatureItem? = null

    fun getChat(): DoubaoAppFeatureItem? {
        return chat
    }

    fun setChat(chat: DoubaoAppFeatureItem?) {
        this.chat = chat
    }

    fun getDeepChat(): DoubaoAppFeatureItem? {
        return deepChat
    }

    fun setDeepChat(deepChat: DoubaoAppFeatureItem?) {
        this.deepChat = deepChat
    }

    fun getAiSearch(): DoubaoAppFeatureItem? {
        return aiSearch
    }

    fun setAiSearch(aiSearch: DoubaoAppFeatureItem?) {
        this.aiSearch = aiSearch
    }

    fun getReasoningSearch(): DoubaoAppFeatureItem? {
        return reasoningSearch
    }

    fun setReasoningSearch(reasoningSearch: DoubaoAppFeatureItem?) {
        this.reasoningSearch = reasoningSearch
    }

    class Builder {
        private var chat: DoubaoAppFeatureItem? = null
        private var deepChat: DoubaoAppFeatureItem? = null
        private var aiSearch: DoubaoAppFeatureItem? = null
        private var reasoningSearch: DoubaoAppFeatureItem? = null

        fun chat(chat: DoubaoAppFeatureItem?): Builder {
            this.chat = chat
            return this
        }

        fun deepChat(deepChat: DoubaoAppFeatureItem?): Builder {
            this.deepChat = deepChat
            return this
        }

        fun aiSearch(aiSearch: DoubaoAppFeatureItem?): Builder {
            this.aiSearch = aiSearch
            return this
        }

        fun reasoningSearch(reasoningSearch: DoubaoAppFeatureItem?): Builder {
            this.reasoningSearch = reasoningSearch
            return this
        }

        fun build(): DoubaoAppFeature {
            val feature: DoubaoAppFeature = com.volcengine.ark.runtime.model.responses.tool.doubaoapp.DoubaoAppFeature()
            feature.setChat(chat)
            feature.setDeepChat(deepChat)
            feature.setAiSearch(aiSearch)
            feature.setReasoningSearch(reasoningSearch)
            return feature
        }
    }


    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.doubaoapp.DoubaoAppFeature.Builder()
        }
    }
}
