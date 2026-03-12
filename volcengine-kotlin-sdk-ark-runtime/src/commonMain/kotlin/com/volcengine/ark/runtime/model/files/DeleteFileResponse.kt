package com.volcengine.ark.runtime.model.files

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class DeleteFileResponse {
    @Override
    fun toString(): String? {
        return "DeleteFileResponse{" +
                "object='" + this.`object` + '\'' +
                ", id='" + id + '\'' +
                ", deleted=" + deleted +
                '}'
    }

    @JsonProperty(value = "object")
    var `object`: String? = null
        set(object) {
            field = this.`object`
        }

    @JsonProperty(value = "id")
    var id: String? = null

    @JsonProperty(value = "deleted")
    var deleted: Boolean? = null
}
