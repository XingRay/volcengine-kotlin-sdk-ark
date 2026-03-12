package com.volcengine.ark.runtime.model.responses.response

import com.fasterxml.jackson.annotation.JsonProperty

class DeleteResponseResponse {
    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("object")
    var `object`: String? = null
        set(object) {
            field = this.`object`
        }

    @JsonProperty("deleted")
    var deleted: Boolean? = null

    @Override
    fun toString(): String? {
        return "DeleteResponseResponse{" +
                "id='" + id + '\'' +
                ", object='" + this.`object` + '\'' +
                ", deleted=" + deleted +
                '}'
    }
}