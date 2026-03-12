package com.volcengine.ark.runtime.model.responses.event.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class DoubaoAppCallInProgressEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_IN_PROGRESS) {
    @SerialName("feature")
    var feature: String? = null


    class Builder {
        private val event: DoubaoAppCallInProgressEvent = com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallInProgressEvent()

        fun feature(feature: String?): Builder {
            event.feature = feature
            return this
        }

        fun build(): DoubaoAppCallInProgressEvent {
            return event
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallInProgressEvent.Builder()
        }
    }
}
