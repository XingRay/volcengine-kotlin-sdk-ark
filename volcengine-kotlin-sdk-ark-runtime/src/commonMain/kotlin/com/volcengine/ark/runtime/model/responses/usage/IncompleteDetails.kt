package com.volcengine.ark.runtime.model.responses.usage
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class IncompleteDetails {
    @SerialName("reason")
    var reason: String? = null

    @SerialName("content_filter")
    private var contentFilter: ContentFilter? = null

    fun getContentFilter(): ContentFilter? {
        return contentFilter
    }

    fun setContentFilter(contentFilter: ContentFilter?) {
        this.contentFilter = contentFilter
    }


    class Builder {
        private var reason: String? = null
        private var contentFilter: ContentFilter? = null

        fun reason(reason: String?): Builder {
            this.reason = reason
            return this
        }

        fun contentFilter(contentFilter: ContentFilter?): Builder {
            this.contentFilter = contentFilter
            return this
        }

        fun build(): IncompleteDetails {
            val incompleteDetails: IncompleteDetails = com.volcengine.ark.runtime.model.responses.usage.IncompleteDetails()
            incompleteDetails.reason = reason
            incompleteDetails.setContentFilter(contentFilter)
            return incompleteDetails
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.usage.IncompleteDetails.Builder()
        }
    }
}