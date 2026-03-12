package com.volcengine.ark.runtime.model.responses.event.contentpart

import com.fasterxml.jackson.annotation.JsonProperty

class ContentPartDoneEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_CONTENT_PART_DONE) {
    @JsonProperty("content_index")
    var contentIndex: Long? = null

    @JsonProperty("part")
    private var part: OutputContentItem? = null

    fun getPart(): OutputContentItem? {
        return part
    }

    fun setPart(part: OutputContentItem?) {
        this.part = part
    }

    @Override
    fun toString(): String? {
        return "ContentPartDoneEvent{" +
                "type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                ", contentIndex=" + contentIndex +
                ", part=" + part +
                '}'
    }
}