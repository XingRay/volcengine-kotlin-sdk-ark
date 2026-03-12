package com.volcengine.ark.runtime.model.responses.response
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class DeleteResponseResponse {
    @SerialName("id")
    var id: String? = null

    @SerialName("object")
    var `object`: String? = null
        set(object) {
            field = this.`object`
        }

    @SerialName("deleted")
    var deleted: Boolean? = null

}