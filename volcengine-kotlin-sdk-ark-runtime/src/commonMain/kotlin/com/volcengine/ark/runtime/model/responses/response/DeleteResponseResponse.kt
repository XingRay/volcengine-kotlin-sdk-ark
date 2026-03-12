package com.volcengine.ark.runtime.model.responses.response
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class DeleteResponseResponse(
    @SerialName("id")
    val id: String? = null,

    @SerialName("object")
    val `object`: String? = null,

    @SerialName("deleted")
    val deleted: Boolean? = null
)