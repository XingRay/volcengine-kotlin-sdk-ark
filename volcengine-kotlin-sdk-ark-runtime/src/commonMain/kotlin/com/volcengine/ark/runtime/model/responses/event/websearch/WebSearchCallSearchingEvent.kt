package com.volcengine.ark.runtime.model.responses.event.websearch

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants

class WebSearchCallSearchingEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_WEB_SEARCH_CALL_SEARCHING) {
    @Override
    fun toString(): String? {
        return "ResponseWebSearchCallSearchingEvent{" +
                "type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                '}'
    }
}