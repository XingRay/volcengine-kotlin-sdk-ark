package com.volcengine.ark.runtime.model.responses.request

import com.fasterxml.jackson.annotation.JsonProperty

class ListInputItemsRequest {
    @JsonProperty("response_id")
    var responseId: String? = null

    @JsonProperty("after")
    var after: String? = null

    @JsonProperty("before")
    var before: String? = null

    @JsonProperty("limit")
    private var limit: Integer? = null

    @JsonProperty("order")
    var order: String? = null

    @JsonProperty("include")
    var include: List<String?>? = null

    fun getLimit(): Integer? {
        return limit
    }

    fun setLimit(limit: Integer?) {
        this.limit = limit
    }

    @Override
    fun toString(): String? {
        return "ListInputItemsRequest{" +
                "responseId='" + responseId + '\'' +
                ", after='" + after + '\'' +
                ", before='" + before + '\'' +
                ", limit=" + limit +
                ", order='" + order + '\'' +
                ", include=" + include +
                '}'
    }

    class Builder {
        private var responseId: String? = null
        private var after: String? = null
        private var before: String? = null
        private var limit: Integer? = null
        private var order: String? = null
        private var include: List<String?>? = null

        fun responseId(responseId: String?): Builder {
            this.responseId = responseId
            return this
        }

        fun after(after: String?): Builder {
            this.after = after
            return this
        }

        fun before(before: String?): Builder {
            this.before = before
            return this
        }

        fun limit(limit: Integer?): Builder {
            this.limit = limit
            return this
        }

        fun order(order: String?): Builder {
            this.order = order
            return this
        }

        fun include(include: List<String?>?): Builder {
            this.include = include
            return this
        }

        fun build(): ListInputItemsRequest {
            val listInputItemsRequest: ListInputItemsRequest = com.volcengine.ark.runtime.model.responses.request.ListInputItemsRequest()
            listInputItemsRequest.responseId = responseId
            listInputItemsRequest.after = after
            listInputItemsRequest.before = before
            listInputItemsRequest.setLimit(limit)
            listInputItemsRequest.order = order
            listInputItemsRequest.include = include
            return listInputItemsRequest
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.request.ListInputItemsRequest.Builder()
        }
    }
}