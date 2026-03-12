package com.volcengine.ark.runtime.model.responses.item.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class DoubaoAppSearchResult {
    @SerialName("text_card")
    private var textCard: DoubaoAppSearchTextItem? = null

    fun getTextCard(): DoubaoAppSearchTextItem? {
        return textCard
    }

    fun setTextCard(textCard: DoubaoAppSearchTextItem?) {
        this.textCard = textCard
    }

    class Builder {
        private var textCard: DoubaoAppSearchTextItem? = null

        fun textCard(textCard: DoubaoAppSearchTextItem?): Builder {
            this.textCard = textCard
            return this
        }

        fun build(): DoubaoAppSearchResult {
            val searchResult: DoubaoAppSearchResult = com.volcengine.ark.runtime.model.responses.item.doubaoapp.DoubaoAppSearchResult()
            searchResult.setTextCard(textCard)
            return searchResult
        }
    }


    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.doubaoapp.DoubaoAppSearchResult.Builder()
        }
    }
}
