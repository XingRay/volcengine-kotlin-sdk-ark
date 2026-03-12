package com.volcengine.ark.runtime.model.files
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class DeleteFileResponse(
    @SerialName(value = "object")
    val `object`: String? = null,

    @SerialName(value = "id")
    val id: String? = null,

    @SerialName(value = "deleted")
    val deleted: Boolean? = null
)
