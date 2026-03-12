package com.volcengine.ark.runtime.model.responses.event.imageprocess

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants

class ImageProcessCallInProgressEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_IN_PROGRESS) {
    @Override
    fun toString(): String? {
        return "ImageProcessCallInProgressEvent{" +
                "type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                '}'
    }
}