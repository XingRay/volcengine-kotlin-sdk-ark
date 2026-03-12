package com.volcengine.ark.runtime.model.files

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UploadFileRequest(
    @SerialName(value = "file")
    // File
    val file: String? = null,

    @SerialName(value = "purpose")
    val purpose: String? = null,

    @SerialName(value = "expire_at")
    val expireAt: Int? = null,

    @SerialName(value = "preprocess_configs")
    val preprocessConfigs: PreprocessConfigs? = null
) {


    companion object {

        class UploadFileRequestBuilder {
            private var file: String? = null
            private var purpose: String? = null
            private var expireAt: Int? = null
            private var preprocessConfigs: PreprocessConfigs? = null

            fun file(file: String?): UploadFileRequestBuilder {
                this.file = file
                return this
            }

            fun purpose(purpose: String?): UploadFileRequestBuilder {
                this.purpose = purpose
                return this
            }

            fun expireAt(expireAt: Int?): UploadFileRequestBuilder {
                this.expireAt = expireAt
                return this
            }

            fun preprocessConfigs(preprocessConfigs: PreprocessConfigs?): UploadFileRequestBuilder {
                this.preprocessConfigs = preprocessConfigs
                return this
            }

            fun build(): UploadFileRequest {
                return UploadFileRequest(
                    file = file,
                    purpose = purpose,
                    expireAt = expireAt,
                    preprocessConfigs = preprocessConfigs
                )
            }
        }

        fun builder(): UploadFileRequestBuilder {
            return UploadFileRequestBuilder()
        }
    }
}
