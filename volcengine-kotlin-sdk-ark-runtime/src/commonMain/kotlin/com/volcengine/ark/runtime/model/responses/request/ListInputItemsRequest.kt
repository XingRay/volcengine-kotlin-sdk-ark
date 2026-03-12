package com.volcengine.ark.runtime.model.responses.request
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ListInputItemsRequest {
    @SerialName("response_id")
    var responseId: String? = null

    @SerialName("after")
    var after: String? = null

    @SerialName("before")
    var before: String? = null

    @SerialName("limit")
    private var limit: Int? = null

    @SerialName("order")
    var order: String? = null

    @SerialName("include")
    var include: List<String?>? = null

    fun getLimit(): Int? {
        return limit
    }

    fun setLimit(limit: Int?) {
        this.limit = limit
    }


    class Builder {
        private var responseId: String? = null
        private var after: String? = null
        private var before: String? = null
        private var limit: Int? = null
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

        fun limit(limit: Int?): Builder {
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