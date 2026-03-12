package com.volcengine.ark.runtime.model.files

import com.volcengine.ark.runtime.exception.ArkErrorDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class FileMeta(
    /**
     * The file identifier, which can be referenced in the API endpoints.
     * 文件标识符,可以在 API 接口中引用。
     */
    @SerialName(value = "id")
    val id: String? = null,

    /**
     * The object type, which is always "file".
     * 对象类型，始终为 "file"
     */
    @SerialName(value = "object")
    val `object`: String? = null,

    /**
     * The size of the file in bytes.
     * 文件的字节大小
     */
    @SerialName(value = "bytes")
    val bytes: Int? = null,

    /**
     * The unix timestamp for when the file was created.
     * 文件创建的 Unix 时间戳，默认转换为 UTC 时间。
     */
    @SerialName(value = "created_at")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdTime: String? = null,

    /**
     * The unix timestamp for when the file will expire.
     * 文件过期的 Unix 时间戳，默认转换为 UTC 时间。
     */
    @SerialName(value = "expire_at")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val expireTime: String? = null,

    /**
     * The name of the file.
     * 文件名
     */
    @SerialName(value = "filename")
    val filename: String? = null,

    /**
     * The intended purpose of the file. Currently, only "user_data" is supported.
     * 文件的预期用途。目前只支持 "user_data"。
     */
    @SerialName(value = "purpose")
    val purpose: String? = null,

    /**
     * The current status of the file, which can be either uploaded, processed, pending, error, deleting or deleted.
     * 文件的当前状态，可以是"processing", "active", "failed".
     */
    @SerialName(value = "status")
    val status: String? = null,

    /**
     * Additional details about the status of the file. If the file is in the error state, this will include a message describing the error.
     * 关于文件状态的额外详细信息。如果文件处于错误状态，这将包括描述错误的消息。
     */
    @SerialName(value = "error")
    val error: ArkErrorDetails? = null,

    @SerialName(value = "preprocess_configs")
    val preprocessConfigs: PreprocessConfigs? = null
) {
    companion object {
        class FileBuilder {
            private var id: String? = null
            private var `object`: String? = null
            private var bytes: Int? = null
            private var createdTime: String? = null
            private var expireTime: String? = null
            private var filename: String? = null
            private var purpose: String? = null
            private var status: String? = null
            private var error: ArkErrorDetails? = null
            private var preprocessConfigs: PreprocessConfigs? = null

            fun id(id: String?): FileBuilder {
                this.id = id
                return this
            }

            fun `object`(`object`: String?): FileBuilder {
                this.`object` = `object`
                return this
            }

            fun bytes(bytes: Int?): FileBuilder {
                this.bytes = bytes
                return this
            }

            fun createdTime(createdTime: String?): FileBuilder {
                this.createdTime = createdTime
                return this
            }

            fun expireTime(expireTime: String?): FileBuilder {
                this.expireTime = expireTime
                return this
            }

            fun filename(filename: String?): FileBuilder {
                this.filename = filename
                return this
            }

            fun purpose(purpose: String?): FileBuilder {
                this.purpose = purpose
                return this
            }

            fun status(status: String?): FileBuilder {
                this.status = status
                return this
            }

            fun error(error: ArkErrorDetails?): FileBuilder {
                this.error = error
                return this
            }

            fun preprocessConfigs(preprocessConfigs: PreprocessConfigs?): FileBuilder {
                this.preprocessConfigs = preprocessConfigs
                return this
            }

            fun build(): FileMeta {
                return FileMeta(
                    id = id,
                    `object` = `object`,
                    bytes = bytes,
                    createdTime = createdTime,
                    expireTime = expireTime,
                    filename = filename,
                    purpose = purpose,
                    status = status,
                    error = error,
                    preprocessConfigs = preprocessConfigs,
                )
            }
        }

        fun aFile(): FileBuilder {
            return FileBuilder()
        }
    }
}
