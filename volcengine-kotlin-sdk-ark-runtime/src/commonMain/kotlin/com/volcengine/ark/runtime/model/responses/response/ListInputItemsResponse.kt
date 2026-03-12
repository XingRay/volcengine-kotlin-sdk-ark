package com.volcengine.ark.runtime.model.responses.response
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ListInputItemsResponse {
    @SerialName("object")
    var `object`: String? = null
        set(object) {
            field = this.`object`
        }

    @SerialName("data")
    private var data: List<BaseItem?>? = null

    @SerialName("first_id")
    var firstId: String? = null

    @SerialName("last_id")
    var lastId: String? = null

    @SerialName("has_more")
    var hasMore: Boolean? = null

    fun getData(): List<BaseItem?>? {
        return data
    }

    fun setData(data: List<BaseItem?>?) {
        this.data = data
    }

}