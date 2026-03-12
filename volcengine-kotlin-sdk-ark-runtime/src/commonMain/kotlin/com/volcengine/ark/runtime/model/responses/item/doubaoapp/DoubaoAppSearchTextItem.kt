package com.volcengine.ark.runtime.model.responses.item.doubaoapp

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class DoubaoAppSearchTextItem {
    @JsonProperty("title")
    var title: String? = null

    @JsonProperty("sitename")
    var siteName: String? = null

    @JsonProperty("url")
    var url: String? = null

    class Builder {
        private var title: String? = null
        private var siteName: String? = null
        private var url: String? = null

        fun title(title: String?): Builder {
            this.title = title
            return this
        }

        fun siteName(siteName: String?): Builder {
            this.siteName = siteName
            return this
        }

        fun url(url: String?): Builder {
            this.url = url
            return this
        }

        fun build(): DoubaoAppSearchTextItem {
            val searchTextItem: DoubaoAppSearchTextItem = com.volcengine.ark.runtime.model.responses.item.doubaoapp.DoubaoAppSearchTextItem()
            searchTextItem.title = title
            searchTextItem.siteName = siteName
            searchTextItem.url = url
            return searchTextItem
        }
    }

    @Override
    fun toString(): String? {
        return "DoubaoAppSearchTextItem{" +
                "title='" + title + '\'' +
                ", siteName='" + siteName + '\'' +
                ", url='" + url + '\'' +
                '}'
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.doubaoapp.DoubaoAppSearchTextItem.Builder()
        }
    }
}
