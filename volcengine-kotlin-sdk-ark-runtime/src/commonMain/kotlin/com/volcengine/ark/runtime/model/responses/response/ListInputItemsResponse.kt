package com.volcengine.ark.runtime.model.responses.response

import com.volcengine.ark.runtime.model.responses.item.BaseItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ListInputItemsResponse(
    @SerialName("object")
    val `object`: String? = null,

    @SerialName("data")
    val data: List<BaseItem?>? = null,

    @SerialName("first_id")
    val firstId: String? = null,

    @SerialName("last_id")
    val lastId: String? = null,

    @SerialName("has_more")
    val hasMore: Boolean? = null
)