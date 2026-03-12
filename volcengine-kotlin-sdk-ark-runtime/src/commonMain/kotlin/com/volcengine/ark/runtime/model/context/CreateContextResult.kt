package com.volcengine.ark.runtime.model.context

import com.volcengine.ark.runtime.model.Usage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CreateContextResult(
    @SerialName("id")
    val id: String? = null,

    @SerialName("model")
    val model: String? = null,

    @SerialName("mode")
    val mode: String? = null,

    @SerialName("ttl")
    val ttl: Int? = null,

    @SerialName("truncation_strategy")
    val truncationStrategy: TruncationStrategy? = null,

    @SerialName("usage")
    val usage: Usage? = null
)
