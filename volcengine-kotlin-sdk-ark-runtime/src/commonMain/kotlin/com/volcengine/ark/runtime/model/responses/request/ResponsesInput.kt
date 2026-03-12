package com.volcengine.ark.runtime.model.responses.request

import com.volcengine.ark.runtime.model.responses.item.BaseItem
import kotlinx.serialization.Serializable

@Serializable
data class ResponsesInput(
    val stringValue: String? = null,
    val listValue: List<BaseItem?>? = null
)