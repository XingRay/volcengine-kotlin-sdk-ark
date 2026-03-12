package com.volcengine.ark.runtime.model.responses.content

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes([JsonSubTypes.Type(value = OutputContentItemText::class, name = ResponsesConstants.CONTENT_ITEM_TYPE_OUTPUT_TEXT)])
abstract class OutputContentItem(@field:JsonProperty("type") var type: String?) 