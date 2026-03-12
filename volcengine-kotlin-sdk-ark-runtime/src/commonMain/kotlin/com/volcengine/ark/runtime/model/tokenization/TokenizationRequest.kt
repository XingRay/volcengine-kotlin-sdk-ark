package com.volcengine.ark.runtime.model.tokenization
import kotlinx.serialization.Serializable

@Serializable
data class TokenizationRequest(
    val model: String? = null,

    val text: List<String?>? = null,

    /**
     * A unique identifier representing your end-user, which will help OpenAI to monitor and detect abuse.
     */
    val user: String? = null
) {
    class Builder private constructor() {
        private var model: String? = null
        private var text: List<String?>? = null
        private var user: String? = null

        fun model(model: String?): Builder {
            this.model = model
            return this
        }

        fun text(text: List<String?>?): Builder {
            this.text = text
            return this
        }

        fun user(user: String?): Builder {
            this.user = user
            return this
        }

        fun build(): TokenizationRequest {
            val tokenizationRequest: TokenizationRequest = com.volcengine.ark.runtime.model.tokenization.TokenizationRequest()
            tokenizationRequest.model = model
            tokenizationRequest.text = text
            tokenizationRequest.user = user
            return tokenizationRequest
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.tokenization.TokenizationRequest.Builder()
        }
    }
}
