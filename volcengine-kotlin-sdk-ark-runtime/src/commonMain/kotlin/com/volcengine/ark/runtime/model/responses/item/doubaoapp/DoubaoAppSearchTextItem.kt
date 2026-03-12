package com.volcengine.ark.runtime.model.responses.item.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class DoubaoAppSearchTextItem {
    @SerialName("title")
    var title: String? = null

    @SerialName("sitename")
    var siteName: String? = null

    @SerialName("url")
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


    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.doubaoapp.DoubaoAppSearchTextItem.Builder()
        }
    }
}
