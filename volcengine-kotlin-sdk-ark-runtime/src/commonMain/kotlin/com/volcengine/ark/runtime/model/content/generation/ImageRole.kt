package com.volcengine.ark.runtime.model.content.generation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ImageRole(val value: String) {

    @SerialName("first_frame")
    FIRST_FRAME("first_frame"),

    @SerialName("last_frame")
    LAST_FRAME("last_frame"),

    @SerialName("reference_image")
    REFERENCE_IMAGE("reference_image"),

}