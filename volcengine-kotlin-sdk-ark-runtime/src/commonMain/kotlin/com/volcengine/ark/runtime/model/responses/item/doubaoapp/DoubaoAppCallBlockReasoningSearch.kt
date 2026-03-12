package com.volcengine.ark.runtime.model.responses.item.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class DoubaoAppCallBlockReasoningSearch : DoubaoAppCallBlock() {
    @SerialName("id")
    var id: String? = null

    @SerialName("type")
    var type: String? = ResponsesConstants.DOBAO_APP_BLOCK_TYPE_REASONING_SEARCH

    @SerialName("summary")
    var summary: String? = null

    @SerialName("queries")
    var queries: List<String?>? = null

    @SerialName("results")
    private var results: List<DoubaoAppSearchResult?>? = null

    @SerialName("status")
    var status: String? = null

    @SerialName("parent_id")
    var parentId: String? = null

    fun getResults(): List<DoubaoAppSearchResult?>? {
        return results
    }

    fun setResults(results: List<DoubaoAppSearchResult?>?) {
        this.results = results
    }

    class Builder {
        private var id: String? = null
        private var summary: String? = null
        private var queries: List<String?>? = null
        private var results: List<DoubaoAppSearchResult?>? = null
        private var status: String? = null
        private var parentId: String? = null

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun summary(summary: String?): Builder {
            this.summary = summary
            return this
        }

        fun queries(queries: List<String?>?): Builder {
            this.queries = queries
            return this
        }

        fun results(results: List<DoubaoAppSearchResult?>?): Builder {
            this.results = results
            return this
        }

        fun status(status: String?): Builder {
            this.status = status
            return this
        }

        fun parentId(parentId: String?): Builder {
            this.parentId = parentId
            return this
        }

        fun build(): DoubaoAppCallBlockReasoningSearch {
            val reasoningSearch: DoubaoAppCallBlockReasoningSearch = com.volcengine.ark.runtime.model.responses.item.doubaoapp.DoubaoAppCallBlockReasoningSearch()
            reasoningSearch.id = id
            reasoningSearch.summary = summary
            reasoningSearch.queries = queries
            reasoningSearch.setResults(results)
            reasoningSearch.status = status
            reasoningSearch.parentId = parentId
            return reasoningSearch
        }
    }


    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.doubaoapp.DoubaoAppCallBlockReasoningSearch.Builder()
        }
    }
}
