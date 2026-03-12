package com.volcengine.ark.runtime.model.files

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ListFilesResponse(
    @SerialName(value = "object")
    val `object`: String? = null,

    @SerialName(value = "data")
    val data: List<FileMeta?>? = null,

    @SerialName(value = "has_more")
    val hasMore: Boolean? = null,

    @SerialName(value = "first_id")
    val firstId: String? = null,

    @SerialName(value = "last_id")
    val lastId: String? = null
) {

    companion object {
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
                return ListFilesResponse(
                    `object` = `object`,
                    data = data,
                    hasMore = hasMore,
                    firstId = firstId,
                    lastId = lastId,
                )
            }

            fun aListFilesResponse(): ListFilesResponseBuilder {
                return ListFilesResponseBuilder()
            }
        }

    }

}
