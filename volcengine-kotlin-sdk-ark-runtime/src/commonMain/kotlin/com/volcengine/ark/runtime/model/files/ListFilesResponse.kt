package com.volcengine.ark.runtime.model.files

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ListFilesResponse {
    @JsonProperty(value = "object")
    var `object`: String? = null
        set(object) {
            field = this.`object`
        }

    @JsonProperty(value = "data")
    private var data: List<FileMeta?>? = null

    @JsonProperty(value = "has_more")
    var hasMore: Boolean? = null

    @JsonProperty(value = "first_id")
    var firstId: String? = null

    @JsonProperty(value = "last_id")
    var lastId: String? = null

    @Override
    fun toString(): String? {
        return "ListFilesResponse{" +
                "object='" + this.`object` + '\'' +
                ", data=" + data +
                ", hasMore=" + hasMore +
                ", firstId='" + firstId + '\'' +
                ", lastId='" + lastId + '\'' +
                '}'
    }

    fun getData(): List<FileMeta?>? {
        return data
    }

    fun setData(data: List<FileMeta?>?) {
        this.data = data
    }

    class ListFilesResponseBuilder private constructor() {
        private var `object`: String? = null
        private var data: List<FileMeta?>? = null
        private var hasMore: Boolean? = null
        private var firstId: String? = null
        private var lastId: String? = null

        fun `object`(`object`: String?): ListFilesResponseBuilder {
            this.`object` = `object`
            return this
        }

        fun data(data: List<FileMeta?>?): ListFilesResponseBuilder {
            this.data = data
            return this
        }

        fun hasMore(hasMore: Boolean?): ListFilesResponseBuilder {
            this.hasMore = hasMore
            return this
        }

        fun firstId(firstId: String?): ListFilesResponseBuilder {
            this.firstId = firstId
            return this
        }

        fun lastId(lastId: String?): ListFilesResponseBuilder {
            this.lastId = lastId
            return this
        }

        fun build(): ListFilesResponse {
            val listFilesResponse: ListFilesResponse = com.volcengine.ark.runtime.model.files.ListFilesResponse()
            listFilesResponse.object = `object`
            listFilesResponse.setData(data)
            listFilesResponse.hasMore = hasMore
            listFilesResponse.firstId = firstId
            listFilesResponse.lastId = lastId
            return listFilesResponse
        }

        companion object {
            fun aListFilesResponse(): ListFilesResponseBuilder {
                return com.volcengine.ark.runtime.model.files.ListFilesResponse.ListFilesResponseBuilder()
            }
        }
    }
}
