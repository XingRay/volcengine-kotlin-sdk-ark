package com.volcengine.ark.runtime.exception

import kotlinx.serialization.Serializable

@Serializable
class ArkAPIError(
    val error: ArkErrorDetails
)
