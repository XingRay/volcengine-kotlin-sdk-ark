package com.volcengine.ark.runtime.model.responses.tool

import com.fasterxml.jackson.annotation.JsonProperty

class ToolWebSearch : ResponsesTool(ResponsesConstants.TOOL_TYPE_WEB_SEARCH) {
    @JsonProperty("limit")
    var limit: Long? = null

    @JsonProperty("user_location")
    private var userLocation: UserLocation? = null

    @JsonProperty("sources")
    var sources: List<String?>? = null

    fun getUserLocation(): UserLocation? {
        return userLocation
    }

    fun setUserLocation(userLocation: UserLocation?) {
        this.userLocation = userLocation
    }

    @Override
    fun toString(): String? {
        return "ToolWebSearch{" +
                "type='" + getType() + '\'' +
                ", limit=" + limit +
                ", userLocation=" + userLocation +
                ", sources=" + sources +
                '}'
    }

    class Builder {
        private var limit: Long? = null
        private var userLocation: UserLocation? = null
        private var sources: List<String?>? = null

        fun limit(limit: Long?): Builder {
            this.limit = limit
            return this
        }

        fun userLocation(userLocation: UserLocation?): Builder {
            this.userLocation = userLocation
            return this
        }

        fun sources(sources: List<String?>?): Builder {
            this.sources = sources
            return this
        }

        fun build(): ToolWebSearch {
            val toolWebSearch: ToolWebSearch = com.volcengine.ark.runtime.model.responses.tool.ToolWebSearch()
            toolWebSearch.limit = limit
            toolWebSearch.setUserLocation(userLocation)
            toolWebSearch.sources = sources
            return toolWebSearch
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.ToolWebSearch.Builder()
        }
    }
}