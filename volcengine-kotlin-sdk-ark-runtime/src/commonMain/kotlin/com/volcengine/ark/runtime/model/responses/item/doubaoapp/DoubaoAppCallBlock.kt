package com.volcengine.ark.runtime.model.responses.item.doubaoapp

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(
    [JsonSubTypes.Type(value = DoubaoAppCallBlockOutputText::class, name = "output_text"), JsonSubTypes.Type(
        value = DoubaoAppCallBlockReasoningText::class,
        name = "reasoning_text"
    ), JsonSubTypes.Type(value = DoubaoAppCallBlockSearch::class, name = "search"), JsonSubTypes.Type(value = DoubaoAppCallBlockReasoningSearch::class, name = "reasoning_search")]
)
abstract class DoubaoAppCallBlock {
    @JsonProperty("type")
    var type: String? = null

    constructor()

    constructor(type: String?) {
        this.type = type
    }
}
