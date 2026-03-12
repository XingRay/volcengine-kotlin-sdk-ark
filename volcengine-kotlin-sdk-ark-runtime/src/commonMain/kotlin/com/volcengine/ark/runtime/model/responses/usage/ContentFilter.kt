package com.volcengine.ark.runtime.model.responses.usage
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class ContentFilter(
    @SerialName("type")
    val type: String? = null,

    @SerialName("details")
    val details: String? = null
) {
    class Builder {
        private var type: String? = null
        private var details: String? = null

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun details(details: String?): Builder {
            this.details = details
            return this
        }

        fun build(): ContentFilter {
            val contentFilter = ContentFilter()
            contentFilter.type = type
            contentFilter.details = details
            return contentFilter
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.usage.ContentFilter.Builder()
        }
    }
}