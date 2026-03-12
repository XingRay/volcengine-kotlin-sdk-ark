package com.volcengine.ark.runtime.model.responses.event.imageprocess
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ImageProcessCallFailedEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_FAILED) {
    @SerialName("action")
    private var action: ImageProcessAction? = null

    @SerialName("arguments")
    private var arguments: ImageProcessArguments? = null

    @SerialName("error")
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

}