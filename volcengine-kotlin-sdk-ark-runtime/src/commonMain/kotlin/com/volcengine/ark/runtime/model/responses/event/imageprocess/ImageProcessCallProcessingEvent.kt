package com.volcengine.ark.runtime.model.responses.event.imageprocess

import com.fasterxml.jackson.annotation.JsonProperty

class ImageProcessCallProcessingEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_PROGRESSING) {
    @JsonProperty("action")
    private var action: ImageProcessAction? = null

    @JsonProperty("arguments")
    private var arguments: ImageProcessArguments? = null

    fun getAction(): ImageProcessAction? {
        return action
    }

    fun setAction(action: ImageProcessAction?) {
        this.action = action
    }

    fun getArguments(): ImageProcessArguments? {
        return arguments
    }

    fun setArguments(arguments: ImageProcessArguments?) {
        this.arguments = arguments
    }

    @Override
    fun toString(): String? {
        return "ImageProcessCallProcessingEvent{" +
                "type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                ", action=" + action +
                ", arguments=" + arguments +
                '}'
    }
}