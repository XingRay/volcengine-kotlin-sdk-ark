package com.volcengine.ark.runtime.model.responses.event.imageprocess

import com.fasterxml.jackson.annotation.JsonProperty

class ImageProcessCallFailedEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_FAILED) {
    @JsonProperty("action")
    private var action: ImageProcessAction? = null

    @JsonProperty("arguments")
    private var arguments: ImageProcessArguments? = null

    @JsonProperty("error")
    private var error: ImageProcessError? = null

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

    fun getError(): ImageProcessError? {
        return error
    }

    fun setError(error: ImageProcessError?) {
        this.error = error
    }

    @Override
    fun toString(): String? {
        return "ImageProcessCallFailedEvent{" +
                "type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                ", action=" + action +
                ", arguments=" + arguments +
                ", error=" + error +
                '}'
    }
}