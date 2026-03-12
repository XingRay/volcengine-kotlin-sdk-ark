package com.volcengine.ark.runtime.model.responses.common

import com.fasterxml.jackson.annotation.JsonProperty

class UserLocation {
    @JsonProperty("type")
    var type: String? = null

    @JsonProperty("city")
    var city: String? = null

    @JsonProperty("country")
    var country: String? = null

    @JsonProperty("region")
    var region: String? = null

    @JsonProperty("timezone")
    var timezone: String? = null

    @Override
    fun toString(): String? {
        return "UserLocation{" +
                "type='" + type + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", timezone='" + timezone + '\'' +
                '}'
    }

    class Builder {
        private var type: String? = null
        private var city: String? = null
        private var country: String? = null
        private var region: String? = null
        private var timezone: String? = null

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun city(city: String?): Builder {
            this.city = city
            return this
        }

        fun country(country: String?): Builder {
            this.country = country
            return this
        }

        fun region(region: String?): Builder {
            this.region = region
            return this
        }

        fun timezone(timezone: String?): Builder {
            this.timezone = timezone
            return this
        }

        fun build(): UserLocation {
            val userLocation: UserLocation = com.volcengine.ark.runtime.model.responses.common.UserLocation()
            userLocation.type = type
            userLocation.city = city
            userLocation.country = country
            userLocation.region = region
            userLocation.timezone = timezone
            return userLocation
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.common.UserLocation.Builder()
        }
    }
}