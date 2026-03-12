package com.volcengine.ark.runtime.model.responses.request

import com.fasterxml.jackson.annotation.JsonProperty

class GetResponseRequest {
    @JsonProperty("response_id")
    var responseId: String? = null

    @Override
    fun toString(): String? {
        return "GetResponseRequest{" +
                "responseId='" + responseId + '\'' +
                '}'
    }

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