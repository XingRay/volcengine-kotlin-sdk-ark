package com.volcengine.ark.runtime.model.responses.event.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class DoubaoAppCallFailedEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_FAILED) {
    @SerialName("error_message")
    var errorMessage: String? = null


    class Builder {
        private val event: DoubaoAppCallFailedEvent = com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallFailedEvent()

        fun errorMessage(errorMessage: String?): Builder {
            event.errorMessage = errorMessage
            return this
        }

        fun build(): DoubaoAppCallFailedEvent {
            return event
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallFailedEvent.Builder()
        }
    }
}
