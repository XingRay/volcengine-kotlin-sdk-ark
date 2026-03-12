package com.volcengine.ark.runtime.model.content.generation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ListContentGenerationTasksRequest {
    @JsonProperty("page_num")
    var pageNum: Int? = null

    @JsonProperty("page_size")
    var pageSize: Int? = null

    var status: String? = null
    var model: String? = null
    var taskIds: List<String?>? = null
    var serviceTier: String? = null

    constructor()

    constructor(pageNum: Int?, pageSize: Int?, status: String?, model: String?, taskIds: List<String?>?) {
        this.pageNum = pageNum
        this.pageSize = pageSize
        this.status = status
        this.model = model
        this.taskIds = taskIds
    }

    @Override
    fun toString(): String? {
        return "ListContentGenerationTasksRequest{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", status='" + status + '\'' +
                ", model='" + model + '\'' +
                ", taskIds=" + taskIds +
                '}'
    }

    class Builder {
        private var pageNum: Int? = null
        private var pageSize: Int? = null
        private var status: String? = null
        private var model: String? = null
        private val taskIds: List<String?> = ArrayList()
        private var serviceTier: String? = null

        fun pageNum(pageNum: Int?): Builder {
            this.pageNum = pageNum
            return this
        }

        fun pageSize(pageSize: Int?): Builder {
            this.pageSize = pageSize
            return this
        }

        fun status(status: String): Builder {
            this.status = status.toString()
            return this
        }

        fun model(model: String?): Builder {
            this.model = model
            return this
        }

        fun serviceTier(serviceTier: String?): Builder {
            this.serviceTier = serviceTier
            return this
        }

        fun taskIds(taskIds: List<String?>?): Builder {
            this.taskIds.clear()
            this.taskIds.addAll(taskIds)
            return this
        }

        fun addTaskId(taskId: String?): Builder {
            this.taskIds.add(taskId)
            return this
        }

        fun build(): ListContentGenerationTasksRequest {
            val req: ListContentGenerationTasksRequest = com.volcengine.ark.runtime.model.content.generation.ListContentGenerationTasksRequest(pageNum, pageSize, status, model, taskIds)
            req.serviceTier = serviceTier
            return req
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.content.generation.ListContentGenerationTasksRequest.Builder()
        }
    }
}
