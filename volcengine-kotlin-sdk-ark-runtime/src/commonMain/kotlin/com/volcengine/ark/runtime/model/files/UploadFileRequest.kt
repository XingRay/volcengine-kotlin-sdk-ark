package com.volcengine.ark.runtime.model.files

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class UploadFileRequest {
    @JsonProperty(value = "file")
    private var file: File? = null

    @JsonProperty(value = "purpose")
    var purpose: String? = null

    @JsonProperty(value = "expire_at")
    private var expireAt: Integer? = null

    @JsonProperty(value = "preprocess_configs")
    private var preprocessConfigs: PreprocessConfigs? = null

    @Override
    fun toString(): String? {
        return "UploadFileRequest{" +
                "file=" + file +
                ", purpose='" + purpose + '\'' +
                ", expireAt=" + expireAt +
                ", preprocessConfigs=" + preprocessConfigs +
                '}'
    }

    fun getFile(): File? {
        return file
    }

    fun setFile(file: File?) {
        this.file = file
    }

    fun getExpireAt(): Integer? {
        return expireAt
    }

    fun setExpireAt(expireAt: Integer?) {
        this.expireAt = expireAt
    }

    fun getPreprocessConfigs(): PreprocessConfigs? {
        return preprocessConfigs
    }

    fun setPreprocessConfigs(preprocessConfigs: PreprocessConfigs?) {
        this.preprocessConfigs = preprocessConfigs
    }

    class UploadFileRequestBuilder private constructor() {
        private var file: File? = null
        private var purpose: String? = null
        private var expireAt: Integer? = null
        private var preprocessConfigs: PreprocessConfigs? = null

        fun file(file: File?): UploadFileRequestBuilder {
            this.file = file
            return this
        }

        fun purpose(purpose: String?): UploadFileRequestBuilder {
            this.purpose = purpose
            return this
        }

        fun expireAt(expireAt: Integer?): UploadFileRequestBuilder {
            this.expireAt = expireAt
            return this
        }

        fun preprocessConfigs(preprocessConfigs: PreprocessConfigs?): UploadFileRequestBuilder {
            this.preprocessConfigs = preprocessConfigs
            return this
        }

        fun build(): UploadFileRequest {
            val uploadFileRequest: UploadFileRequest = com.volcengine.ark.runtime.model.files.UploadFileRequest()
            uploadFileRequest.setFile(file)
            uploadFileRequest.purpose = purpose
            uploadFileRequest.setExpireAt(expireAt)
            uploadFileRequest.setPreprocessConfigs(preprocessConfigs)
            return uploadFileRequest
        }

        companion object {
            fun anUploadFileRequest(): UploadFileRequestBuilder {
                return com.volcengine.ark.runtime.model.files.UploadFileRequest.UploadFileRequestBuilder()
            }
        }
    }

    companion object {
        fun builder(): UploadFileRequestBuilder {
            return com.volcengine.ark.runtime.model.files.UploadFileRequest.UploadFileRequestBuilder()
        }
    }
}
