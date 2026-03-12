package com.volcengine.ark.runtime.model.responses.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserLocation(
    @SerialName("type")
    val type: String? = null,

    @SerialName("city")
    val city: String? = null,

    @SerialName("country")
    val country: String? = null,

    @SerialName("region")
    val region: String? = null,

    @SerialName("timezone")
    val timezone: String? = null
)