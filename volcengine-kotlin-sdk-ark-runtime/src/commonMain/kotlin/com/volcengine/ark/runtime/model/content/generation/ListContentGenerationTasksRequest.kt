package com.volcengine.ark.runtime.model.content.generation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ListContentGenerationTasksRequest(
    @SerialName("page_num")
    val pageNum: Int? = null,

    @SerialName("page_size")
    val pageSize: Int? = null,

    val status: String? = null,
    val model: String? = null,
    val taskIds: List<String>? = null,
    val serviceTier: String? = null
) {
    companion object {
        class Builder {
            private var pageNum: Int? = null
            private var pageSize: Int? = null
            private var status: String? = null
            private var model: String? = null
            private val taskIds: MutableList<String> = mutableListOf()
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
                this.status = status
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

            fun taskIds(taskIds: List<String>): Builder {
                this.taskIds.clear()
                this.taskIds.addAll(taskIds)
                return this
            }

            fun addTaskId(taskId: String): Builder {
                this.taskIds.add(taskId)
                return this
            }

            fun build(): ListContentGenerationTasksRequest {
                return ListContentGenerationTasksRequest(
                    pageNum = pageNum,
                    pageSize = pageSize,
                    status = status,
                    model = model,
                    taskIds = taskIds,
                    serviceTier = serviceTier,
                )
            }
        }

        fun builder(): Builder {
            return Builder()
        }
    }

}
