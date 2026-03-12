package com.volcengine.ark.runtime.model.tokenization
import kotlinx.serialization.Serializable


@Serializable
data class TokenizationResult(
    /**
     * Unique id assigned to this tokenization
     */
    val id: String? = null,

    /**
     * The creation time in epoch seconds.
     */
    val created: String? = null,

    /**
     * The model used for generating tokenization
     */
    val model: String? = null,

    /**
     * The type of object returned, should be "list"
     */
    val `object`: String? = null,

    /**
     * A list of the calculated tokenization
     */
    val data: List<Tokenization?>? = null
)
