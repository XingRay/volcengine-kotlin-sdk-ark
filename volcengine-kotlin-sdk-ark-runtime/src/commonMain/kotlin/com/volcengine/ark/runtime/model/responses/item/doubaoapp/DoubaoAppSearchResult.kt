package com.volcengine.ark.runtime.model.responses.item.doubaoapp

import com.fasterxml.jackson.annotation.JsonProperty

class DoubaoAppSearchResult {
    @JsonProperty("text_card")
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

    @Override
    fun toString(): String? {
        return "DoubaoAppSearchResult{" +
                "textCard=" + textCard +
                '}'
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.doubaoapp.DoubaoAppSearchResult.Builder()
        }
    }
}
