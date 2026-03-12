package com.volcengine.ark.runtime.model.content.generation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class GetContentGenerationTaskRequest {
    @JsonProperty("task_id")
    var taskId: String? = null

    constructor()

    constructor(taskId: String?) {
        this.taskId = taskId
    }

    @Override
    fun toString(): String? {
        return "GetContentGenerationTaskRequest{" +
                "taskId='" + taskId + '\'' +
                '}'
    }

    class Builder private constructor() {
        private var taskId: String? = null

        fun taskId(taskId: String?): Builder {
            this.taskId = taskId
            return this
        }

        fun build(): GetContentGenerationTaskRequest {
            val request: GetContentGenerationTaskRequest = com.volcengine.ark.runtime.model.content.generation.GetContentGenerationTaskRequest()
            request.taskId = taskId
            return request
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.content.generation.GetContentGenerationTaskRequest.Builder()
        }
    }
}
