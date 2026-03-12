package com.volcengine.ark.runtime.model.files

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ListFilesRequest(
    @SerialName(value = "after")
    val after: String? = null,

    @SerialName(value = "limit")
    val limit: Int? = null,

    @SerialName(value = "order")
    val order: String? = null,

    @SerialName(value = "purpose")
    val purpose: String? = null
) {


    companion object {
        class ListFilesRequestBuilder {
            private var after: String? = null
            private var limit: Int? = null
            private var order: String? = null
            private var purpose: String? = null

            fun after(after: String?): ListFilesRequestBuilder {
                this.after = after
                return this
            }

            fun limit(limit: Int?): ListFilesRequestBuilder {
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
                return ListFilesRequest(
                    after = after,
                    limit = limit,
                    order = order,
                    purpose = purpose,
                )
            }
        }

        fun aListFilesRequest(): ListFilesRequestBuilder {
            return ListFilesRequestBuilder()
        }

    }

}
