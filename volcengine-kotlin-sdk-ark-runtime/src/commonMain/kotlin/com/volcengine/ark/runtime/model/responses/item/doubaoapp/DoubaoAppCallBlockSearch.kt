package com.volcengine.ark.runtime.model.responses.item.doubaoapp

import com.fasterxml.jackson.annotation.JsonProperty

class DoubaoAppCallBlockSearch : DoubaoAppCallBlock() {
    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("type")
    var type: String? = ResponsesConstants.DOBAO_APP_BLOCK_TYPE_SEARCH

    @JsonProperty("summary")
    var summary: String? = null

    @JsonProperty("queries")
    var queries: List<String?>? = null

    @JsonProperty("results")
    private var results: List<DoubaoAppSearchResult?>? = null

    @JsonProperty("status")
    var status: String? = null

    @JsonProperty("parent_id")
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

        fun build(): DoubaoAppCallBlockSearch {
            val search: DoubaoAppCallBlockSearch = com.volcengine.ark.runtime.model.responses.item.doubaoapp.DoubaoAppCallBlockSearch()
            search.id = id
            search.summary = summary
            search.queries = queries
            search.setResults(results)
            search.status = status
            search.parentId = parentId
            return search
        }
    }

    @Override
    fun toString(): String? {
        return "DoubaoAppCallBlockSearch{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", summary='" + summary + '\'' +
                ", queries=" + queries +
                ", results=" + results +
                ", status='" + status + '\'' +
                ", parentId='" + parentId + '\'' +
                '}'
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.doubaoapp.DoubaoAppCallBlockSearch.Builder()
        }
    }
}
