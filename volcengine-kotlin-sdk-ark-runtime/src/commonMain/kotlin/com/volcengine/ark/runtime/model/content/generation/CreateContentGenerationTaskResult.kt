package com.volcengine.ark.runtime.model.content.generation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class CreateContentGenerationTaskResult {
    @JsonProperty("id")
    var id: String? = null

    constructor()

    constructor(id: String?) {
        this.id = id
    }

    @Override
    fun toString(): String? {
        return "CreateContentGenerationTaskResult{" +
                "id='" + id + '\'' +
                '}'
    }

    class Builder private constructor() {
        private var id: String? = null

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun build(): CreateContentGenerationTaskResult {
            val createContentGenerationTaskResult: CreateContentGenerationTaskResult = com.volcengine.ark.runtime.model.content.generation.CreateContentGenerationTaskResult()
            createContentGenerationTaskResult.id = id
            return createContentGenerationTaskResult
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.content.generation.CreateContentGenerationTaskResult.Builder()
        }
    }
}
