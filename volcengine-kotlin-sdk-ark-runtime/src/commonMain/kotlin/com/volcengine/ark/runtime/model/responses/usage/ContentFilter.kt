package com.volcengine.ark.runtime.model.responses.usage

import com.fasterxml.jackson.annotation.JsonProperty

class ContentFilter {
    @JsonProperty("type")
    var type: String? = null

    @JsonProperty("details")
    var details: String? = null

    @Override
    fun toString(): String? {
        return "ContentFilter{" +
                "type='" + type + '\'' +
                ", details='" + details + '\'' +
                '}'
    }

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