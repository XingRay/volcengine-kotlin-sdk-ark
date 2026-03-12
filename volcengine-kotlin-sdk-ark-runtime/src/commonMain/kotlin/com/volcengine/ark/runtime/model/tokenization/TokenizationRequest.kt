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
)