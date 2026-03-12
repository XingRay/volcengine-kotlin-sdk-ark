package com.volcengine.ark.runtime.model.responses.event.outputtext

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import com.volcengine.ark.runtime.model.responses.tool.websearch.Annotation
import com.volcengine.ark.runtime.model.responses.event.StreamEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_TEXT_ANNOTATION_ADDED)
data class AnnotationAddedEvent(
    @SerialName("type")
    override val type: String = ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_TEXT_ANNOTATION_ADDED,
    @SerialName("sequence_number")
    override val sequenceNumber: Long? = null,
    @SerialName("output_index")
    val outputIndex: Long? = null,
    @SerialName("item_id")
    val itemId: String? = null,
    @SerialName("content_index")
    val contentIndex: Long? = null,
    @SerialName("delta")
    val delta: String? = null,
    @SerialName("text")
    val text: String? = null,
    @SerialName("annotation_index")
    val annotationIndex: Long? = null,
    @SerialName("annotation")
    val annotation: Annotation? = null
) : StreamEvent(type) {
    constructor() : this(ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_TEXT_ANNOTATION_ADDED)
}