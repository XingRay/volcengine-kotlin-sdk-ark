package com.volcengine.ark.runtime.model.files

import com.fasterxml.jackson.annotation.JsonFormat

@JsonIgnoreProperties(ignoreUnknown = true)
class FileMeta {
    /**
     * The file identifier, which can be referenced in the API endpoints.
     * 文件标识符,可以在 API 接口中引用。
     */
    @JsonProperty(value = "id")
    var id: String? = null

    /**
     * The object type, which is always "file".
     * 对象类型，始终为 "file"
     */
    @JsonProperty(value = "object")
    var `object`: String? = null
        set(object) {
            field = this.`object`
        }

    /**
     * The size of the file in bytes.
     * 文件的字节大小
     */
    @JsonProperty(value = "bytes")
    private var bytes: Integer? = null

    /**
     * The unix timestamp for when the file was created.
     * 文件创建的 Unix 时间戳，默认转换为 UTC 时间。
     */
    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createdTime: String? = null

    /**
     * The unix timestamp for when the file will expire.
     * 文件过期的 Unix 时间戳，默认转换为 UTC 时间。
     */
    @JsonProperty(value = "expire_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var expireTime: String? = null

    /**
     * The name of the file.
     * 文件名
     */
    @JsonProperty(value = "filename")
    var filename: String? = null

    /**
     * The intended purpose of the file. Currently, only "user_data" is supported.
     * 文件的预期用途。目前只支持 "user_data"。
     */
    @JsonProperty(value = "purpose")
    var purpose: String? = null

    /**
     * The current status of the file, which can be either uploaded, processed, pending, error, deleting or deleted.
     * 文件的当前状态，可以是"processing", "active", "failed".
     */
    @JsonProperty(value = "status")
    var status: String? = null

    /**
     * Additional details about the status of the file. If the file is in the error state, this will include a message describing the error.
     * 关于文件状态的额外详细信息。如果文件处于错误状态，这将包括描述错误的消息。
     */
    @JsonProperty(value = "error")
    private var error: ArkErrorDetails? = null

    @JsonProperty(value = "preprocess_configs")
    private var preprocessConfigs: PreprocessConfigs? = null

    @Override
    fun toString(): String? {
        return "FileMeta{" +
                "id='" + id + '\'' +
                ", object='" + this.`object` + '\'' +
                ", bytes=" + bytes +
                ", createdTime='" + createdTime + '\'' +
                ", expireTime='" + expireTime + '\'' +
                ", filename='" + filename + '\'' +
                ", purpose='" + purpose + '\'' +
                ", status='" + status + '\'' +
                ", error=" + error +
                ", preprocessConfigs=" + preprocessConfigs +
                '}'
    }

    fun getBytes(): Integer? {
        return bytes
    }

    fun setBytes(bytes: Integer?) {
        this.bytes = bytes
    }

    fun getError(): ArkErrorDetails? {
        return error
    }

    fun setError(error: ArkErrorDetails?) {
        this.error = error
    }

    fun getPreprocessConfigs(): PreprocessConfigs? {
        return preprocessConfigs
    }

    fun setPreprocessConfigs(preprocessConfigs: PreprocessConfigs?) {
        this.preprocessConfigs = preprocessConfigs
    }

    class FileBuilder private constructor() {
        private var id: String? = null
        private var `object`: String? = null
        private var bytes: Integer? = null
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

        fun bytes(bytes: Integer?): FileBuilder {
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
            val fileMeta: FileMeta = com.volcengine.ark.runtime.model.files.FileMeta()
            fileMeta.id = id
            fileMeta.object = `object`
            fileMeta.setBytes(bytes)
            fileMeta.createdTime = createdTime
            fileMeta.expireTime = expireTime
            fileMeta.filename = filename
            fileMeta.purpose = purpose
            fileMeta.status = status
            fileMeta.setError(error)
            fileMeta.setPreprocessConfigs(preprocessConfigs)
            return fileMeta
        }

        companion object {
            fun aFile(): FileBuilder {
                return FileBuilder()
            }
        }
    }
}
