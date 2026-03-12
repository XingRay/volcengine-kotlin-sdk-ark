package com.volcengine.ark.runtime.model.responses.event.outputtext
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class OutputTextDeltaEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_TEXT_DELTA) {
    @SerialName("content_index")
    var contentIndex: Long? = null

    @SerialName("delta")
    var delta: String? = null

}