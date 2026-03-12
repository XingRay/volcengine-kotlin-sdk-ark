package com.volcengine.ark.runtime.model.responses.request
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class GetResponseRequest(
    @SerialName("response_id")
    val responseId: String? = null
) {
    class Builder {
        private var responseId: String? = null

        fun responseId(responseId: String?): Builder {
            this.responseId = responseId
            return this
        }

        fun build(): GetResponseRequest {
            val getResponseRequest: GetResponseRequest = com.volcengine.ark.runtime.model.responses.request.GetResponseRequest()
            getResponseRequest.responseId = responseId
            return getResponseRequest
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.request.GetResponseRequest.Builder()
        }
    }
}