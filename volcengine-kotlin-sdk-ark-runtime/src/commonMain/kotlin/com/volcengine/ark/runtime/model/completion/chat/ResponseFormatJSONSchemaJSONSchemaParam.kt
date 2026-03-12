package com.volcengine.ark.runtime.model.completion.chat

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ResponseFormatJSONSchemaJSONSchemaParam {
    var name: String?
    var description: String? = null
    var schema: JsonNode? = null
    var isStrict: Boolean = false

    @Override
    fun toString(): String? {
        return "ResponseFormatJSONSchemaJSONSchemaParam{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", schema=" + schema +
                ", strict=" + this.isStrict +
                '}'
    }

    fun getSchema(): JsonNode? {
        return schema
    }

    fun setSchema(schema: JsonNode?) {
        this.schema = schema
    }


    constructor(name: String?) {
        this.name = name
    }

    constructor(name: String?, description: String?, schema: JsonNode?, strict: Boolean) {
        this.name = name
        this.description = description
        this.schema = schema
        this.isStrict = strict
    }
}