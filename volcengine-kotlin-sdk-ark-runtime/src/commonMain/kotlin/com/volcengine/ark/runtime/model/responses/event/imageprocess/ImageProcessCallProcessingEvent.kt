package com.volcengine.ark.runtime.model.responses.event.imageprocess
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ImageProcessCallProcessingEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_PROGRESSING) {
    @SerialName("action")
    private var action: ImageProcessAction? = null

    @SerialName("arguments")
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

}