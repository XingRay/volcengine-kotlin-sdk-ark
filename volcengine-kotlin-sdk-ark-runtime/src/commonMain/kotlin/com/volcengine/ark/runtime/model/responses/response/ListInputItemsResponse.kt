package com.volcengine.ark.runtime.model.responses.response

import com.fasterxml.jackson.annotation.JsonProperty

class ListInputItemsResponse {
    @JsonProperty("object")
    var `object`: String? = null
        set(object) {
            field = this.`object`
        }

    @JsonProperty("data")
    private var data: List<BaseItem?>? = null

    @JsonProperty("first_id")
    var firstId: String? = null

    @JsonProperty("last_id")
    var lastId: String? = null

    @JsonProperty("has_more")
    var hasMore: Boolean? = null

    fun getData(): List<BaseItem?>? {
        return data
    }

    fun setData(data: List<BaseItem?>?) {
        this.data = data
    }

    @Override
    fun toString(): String? {
        return "ListInputItemsResponse{" +
                "object='" + this.`object` + '\'' +
                ", data=" + data +
                ", firstId='" + firstId + '\'' +
                ", lastId='" + lastId + '\'' +
                ", hasMore=" + hasMore +
                '}'
    }
}