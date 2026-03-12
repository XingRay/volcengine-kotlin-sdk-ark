package com.volcengine.ark.runtime.model.responses.event.outputtext
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class AnnotationAddedEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_TEXT_ANNOTATION_ADDED) {
    @SerialName("content_index")
    var contentIndex: Long? = null

    @SerialName("delta")
    var delta: String? = null

    @SerialName("text")
    var text: String? = null

    @SerialName("annotation_index")
    var annotationIndex: Long? = null

    @SerialName("annotation")
    var annotation: Annotation? = null

}