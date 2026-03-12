package com.volcengine.ark.runtime.model.responses.request

import com.fasterxml.jackson.annotation.JsonProperty

class DeleteResponseRequest {
    @JsonProperty("response_id")
    var responseId: String? = null

    @Override
    fun toString(): String? {
        return "DeleteResponseRequest{" +
                "responseId='" + responseId + '\'' +
                '}'
    }

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