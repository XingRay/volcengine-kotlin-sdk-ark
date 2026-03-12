package com.volcengine.ark.runtime.model.responses.content

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(
    [JsonSubTypes.Type(value = InputContentItemText::class, name = ResponsesConstants.CONTENT_ITEM_TYPE_INPUT_TEXT), JsonSubTypes.Type(
        value = InputContentItemImage::class,
        name = ResponsesConstants.CONTENT_ITEM_TYPE_INPUT_IMAGE
    ), JsonSubTypes.Type(value = InputContentItemVideo::class, name = ResponsesConstants.CONTENT_ITEM_TYPE_INPUT_VIDEO), JsonSubTypes.Type(
        value = InputContentItemFile::class,
        name = ResponsesConstants.CONTENT_ITEM_TYPE_INPUT_FILE
    )]
)
abstract class InputContentItem(@field:JsonProperty("type") var type: String?) 