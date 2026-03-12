package com.volcengine.ark.runtime.model.responses.event.websearch

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants

class WebSearchCallInProgressEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_WEB_SEARCH_CALL_IN_PROGRESS) {
    @Override
    fun toString(): String? {
        return "ResponseWebSearchCallInProgressEvent{" +
                "type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                '}'
    }
}