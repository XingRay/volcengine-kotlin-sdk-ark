package com.volcengine.ark.runtime.model.responses.event.websearch;

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants;
import com.volcengine.ark.runtime.model.responses.event.ItemEvent;

public class WebSearchCallInProgressEvent extends ItemEvent {

    public WebSearchCallInProgressEvent() {
        super(ResponsesConstants.EVENT_TYPE_RESPONSE_WEB_SEARCH_CALL_IN_PROGRESS);
    }

    @Override
    public String toString() {
        return "ResponseWebSearchCallInProgressEvent{" +
                "type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                '}';
    }


}