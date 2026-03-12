package com.volcengine.ark.runtime.model.responses.event.functioncall
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class FunctionCallArgumentsDoneEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_FUNCTION_CALL_ARGUMENTS_DONE) {
    @SerialName("arguments")
    var arguments: String? = null

}