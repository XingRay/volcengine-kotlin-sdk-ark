package com.volcengine.ark.runtime.model.responses.event.contentpart
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ContentPartAddedEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_CONTENT_PART_ADDED) {
    @SerialName("content_index")
    var contentIndex: Long? = null

    @SerialName("part")
    private var part: OutputContentItem? = null

    fun getPart(): OutputContentItem? {
        return part
    }

    fun setPart(part: OutputContentItem?) {
        this.part = part
    }

}