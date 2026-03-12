package com.volcengine.ark.runtime.model.responses.tool

import com.fasterxml.jackson.annotation.JsonProperty

class ToolDoubaoApp : ResponsesTool(ResponsesConstants.TOOL_TYPE_DOUBAO_APP) {
    @JsonProperty("feature")
    private var feature: DoubaoAppFeature? = null

    @JsonProperty("user_location")
    private var userLocation: UserLocation? = null

    fun getFeature(): DoubaoAppFeature? {
        return feature
    }

    fun setFeature(feature: DoubaoAppFeature?) {
        this.feature = feature
    }

    fun getUserLocation(): UserLocation? {
        return userLocation
    }

    fun setUserLocation(userLocation: UserLocation?) {
        this.userLocation = userLocation
    }

    class Builder {
        private var feature: DoubaoAppFeature? = null
        private var userLocation: UserLocation? = null

        fun feature(feature: DoubaoAppFeature?): Builder {
            this.feature = feature
            return this
        }

        fun userLocation(userLocation: UserLocation?): Builder {
            this.userLocation = userLocation
            return this
        }

        fun build(): ToolDoubaoApp {
            val toolDoubaoApp: ToolDoubaoApp = com.volcengine.ark.runtime.model.responses.tool.ToolDoubaoApp()
            toolDoubaoApp.setFeature(feature)
            toolDoubaoApp.setUserLocation(userLocation)
            return toolDoubaoApp
        }
    }

    @Override
    fun toString(): String? {
        return "ToolDoubaoApp{" +
                "type='" + getType() + '\'' +
                ", feature=" + feature +
                ", userLocation=" + userLocation +
                '}'
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.ToolDoubaoApp.Builder()
        }
    }
}
