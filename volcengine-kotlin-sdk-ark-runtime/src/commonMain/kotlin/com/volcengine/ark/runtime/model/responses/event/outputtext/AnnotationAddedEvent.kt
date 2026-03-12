package com.volcengine.ark.runtime.model.responses.event.outputtext

import com.fasterxml.jackson.annotation.JsonProperty

class AnnotationAddedEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_TEXT_ANNOTATION_ADDED) {
    @JsonProperty("content_index")
    var contentIndex: Long? = null

    @JsonProperty("delta")
    var delta: String? = null

    @JsonProperty("text")
    var text: String? = null

    @JsonProperty("annotation_index")
    var annotationIndex: Long? = null

    @JsonProperty("annotation")
    var annotation: Annotation? = null

    @Override
    fun toString(): String? {
        return "AnnotationAddedEvent{" +
                "type='" + getType() + '\'' +
                ", sequenceNumber=" + getSequenceNumber() +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                ", contentIndex=" + contentIndex +
                ", delta='" + delta + '\'' +
                ", text='" + text + '\'' +
                ", annotationIndex=" + annotationIndex +
                ", annotation=" + annotation +
                '}'
    }
}