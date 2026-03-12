package com.volcengine.ark.runtime.model.responses.event.websearch

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants

class WebSearchCallCompletedEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_WEB_SEARCH_CALL_COMPLETED) {
    @Override
    fun toString(): String? {
        return "WebSearchCallCompletedEvent{" +
                "type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                '}'
    }
}