package com.volcengine.ark.runtime.model.files

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ListFilesRequest {
    fun getLimit(): Integer? {
        return limit
    }

    fun setLimit(limit: Integer?) {
        this.limit = limit
    }

    @JsonProperty(value = "after")
    var after: String? = null

    @JsonProperty(value = "limit")
    private var limit: Integer? = null

    @JsonProperty(value = "order")
    var order: String? = null

    @JsonProperty(value = "purpose")
    var purpose: String? = null

    @Override
    fun toString(): String? {
        return "ListFilesRequest{" +
                "after='" + after + '\'' +
                ", limit=" + limit +
                ", order='" + order + '\'' +
                ", purpose='" + purpose + '\'' +
                '}'
    }

    class ListFilesRequestBuilder private constructor() {
        private var after: String? = null
        private var limit: Integer? = null
        private var order: String? = null
        private var purpose: String? = null

        fun after(after: String?): ListFilesRequestBuilder {
            this.after = after
            return this
        }

        fun limit(limit: Integer?): ListFilesRequestBuilder {
            this.limit = limit
            return this
        }

        fun order(order: String?): ListFilesRequestBuilder {
            this.order = order
            return this
        }

        fun purpose(purpose: String?): ListFilesRequestBuilder {
            this.purpose = purpose
            return this
        }

        fun build(): ListFilesRequest {
            val listFilesRequest: ListFilesRequest = com.volcengine.ark.runtime.model.files.ListFilesRequest()
            listFilesRequest.after = after
            listFilesRequest.setLimit(limit)
            listFilesRequest.order = order
            listFilesRequest.purpose = purpose
            return listFilesRequest
        }

        companion object {
            fun aListFilesRequest(): ListFilesRequestBuilder {
                return com.volcengine.ark.runtime.model.files.ListFilesRequest.ListFilesRequestBuilder()
            }
        }
    }
}
