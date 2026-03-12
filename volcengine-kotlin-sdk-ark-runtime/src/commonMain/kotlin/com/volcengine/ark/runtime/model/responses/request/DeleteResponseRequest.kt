package com.volcengine.ark.runtime.model.responses.request
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class DeleteResponseRequest(
    @SerialName("response_id")
    val responseId: String? = null
)