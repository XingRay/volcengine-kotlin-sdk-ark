package com.volcengine.ark.runtime.exception

import kotlinx.serialization.Serializable

@Serializable
class ArkAPIError(
    val error: ArkErrorDetails
)

@Serializable
data class ArkErrorDetails(
    val message: String? = null,
    val type: String? = null,
    val param: String? = null,
    val code: String? = null,
)