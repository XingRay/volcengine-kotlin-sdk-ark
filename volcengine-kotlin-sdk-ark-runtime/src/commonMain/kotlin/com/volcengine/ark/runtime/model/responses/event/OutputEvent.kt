package com.volcengine.ark.runtime.model.responses.event
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class OutputEvent(type: String?) : StreamEvent(type) {
    @SerialName("output_index")
    var outputIndex: Long? = null

}