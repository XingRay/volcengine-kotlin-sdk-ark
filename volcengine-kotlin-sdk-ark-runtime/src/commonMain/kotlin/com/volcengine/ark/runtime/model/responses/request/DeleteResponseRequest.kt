package com.volcengine.ark.runtime.model.responses.request
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class DeleteResponseRequest(
    @SerialName("response_id")
    val responseId: String? = null
) {
    class Builder {
        private var responseId: String? = null

        fun responseId(responseId: String?): Builder {
            this.responseId = responseId
            return this
        }

        fun build(): DeleteResponseRequest {
            val deleteResponseRequest: DeleteResponseRequest = com.volcengine.ark.runtime.model.responses.request.DeleteResponseRequest()
            deleteResponseRequest.responseId = responseId
            return deleteResponseRequest
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.request.DeleteResponseRequest.Builder()
        }
    }
}