package com.volcengine.ark.runtime.model.responses.tool
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ToolWebSearch : ResponsesTool(ResponsesConstants.TOOL_TYPE_WEB_SEARCH) {
    @SerialName("limit")
    var limit: Long? = null

    @SerialName("user_location")
    private var userLocation: UserLocation? = null

    @SerialName("sources")
    var sources: List<String?>? = null

    fun getUserLocation(): UserLocation? {
        return userLocation
    }

    fun setUserLocation(userLocation: UserLocation?) {
        this.userLocation = userLocation
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