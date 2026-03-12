package com.volcengine.ark.runtime.model.tokenization

class TokenizationRequest {
    var model: String? = null

    var text: List<String?>? = null

    /**
     * A unique identifier representing your end-user, which will help OpenAI to monitor and detect abuse.
     */
    var user: String? = null

    constructor(model: String?, text: List<String?>?) {
        this.model = model
        this.text = text
    }

    constructor(model: String?, text: List<String?>?, user: String?) {
        this.model = model
        this.text = text
        this.user = user
    }

    constructor()

    @Override
    fun toString(): String? {
        return "TokenizationRequest{" +
                "model='" + model + '\'' +
                ", text=" + text +
                ", user='" + user + '\'' +
                '}'
    }

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
