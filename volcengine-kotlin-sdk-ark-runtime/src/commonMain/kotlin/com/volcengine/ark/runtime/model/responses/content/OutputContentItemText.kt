package com.volcengine.ark.runtime.model.responses.content

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import com.volcengine.ark.runtime.model.responses.tool.websearch.Annotation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.CONTENT_ITEM_TYPE_OUTPUT_TEXT)
data class OutputContentItemText(
    @SerialName("type")
    override val type: String = ResponsesConstants.CONTENT_ITEM_TYPE_OUTPUT_TEXT,

    @SerialName("text")
    val text: String? = null,

    @SerialName("annotations")
    val annotations: List<Annotation>? = null
) : OutputContentItem(type) {
    constructor() : this(ResponsesConstants.CONTENT_ITEM_TYPE_OUTPUT_TEXT)
} 