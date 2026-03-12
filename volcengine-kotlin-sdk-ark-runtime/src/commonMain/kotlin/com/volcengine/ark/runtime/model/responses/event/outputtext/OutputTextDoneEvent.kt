package com.volcengine.ark.runtime.model.responses.event.outputtext
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class OutputTextDoneEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_TEXT_DONE) {
    @SerialName("content_index")
    var contentIndex: Long? = null

    @SerialName("text")
    var text: String? = null

}