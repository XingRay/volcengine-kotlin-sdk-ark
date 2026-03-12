package com.volcengine.ark.runtime.model.responses.request
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ListInputItemsRequest(
    @SerialName("response_id")
    val responseId: String? = null,

    @SerialName("after")
    val after: String? = null,

    @SerialName("before")
    val before: String? = null,

    @SerialName("limit")
    val limit: Int? = null,

    @SerialName("order")
    val order: String? = null,

    @SerialName("include")
    val include: List<String?>? = null
)