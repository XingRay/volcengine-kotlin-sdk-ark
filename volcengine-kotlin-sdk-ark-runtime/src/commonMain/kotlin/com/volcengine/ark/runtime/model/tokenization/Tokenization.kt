package com.volcengine.ark.runtime.model.tokenization
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Tokenization(
    val index: Int? = null,

    val `object`: String? = null,

    @SerialName("total_tokens")
    val totalTokens: Int? = null,

    @SerialName("token_ids")
    val tokenIds: List<Int?>? = null,

    @SerialName("offset_mapping")
    val offsetMapping: List<List<Int?>?>? = null
)
