package com.volcengine.ark.runtime.model.responses.content

import com.fasterxml.jackson.annotation.JsonProperty

class InputContentItemFile : InputContentItem(ResponsesConstants.CONTENT_ITEM_TYPE_INPUT_FILE) {
    @JsonProperty("file_url")
    var fileUrl: String? = null

    @JsonProperty("file_id")
    var fileId: String? = null

    @JsonProperty("file_data")
    var fileData: String? = null

    @JsonProperty("filename")
    var fileName: String? = null

    @Override
    fun toString(): String? {
        return "InputContentItemFile{" +
                "fileUrl='" + fileUrl + '\'' +
                ", fileId='" + fileId + '\'' +
                ", fileData='" + fileData + '\'' +
                ", fileName='" + fileName + '\'' +
                '}'
    }

    class InputContentItemFileBuilder private constructor() {
        private var fileUrl: String? = null
        private var fileId: String? = null
        private var fileData: String? = null
        private var fileName: String? = null

        fun fileUrl(fileUrl: String?): InputContentItemFileBuilder {
            this.fileUrl = fileUrl
            return this
        }

        fun fileId(fileId: String?): InputContentItemFileBuilder {
            this.fileId = fileId
            return this
        }

        fun fileData(fileData: String?): InputContentItemFileBuilder {
            this.fileData = fileData
            return this
        }

        fun fileName(fileName: String?): InputContentItemFileBuilder {
            this.fileName = fileName
            return this
        }

        fun build(): InputContentItemFile {
            val inputContentItemFile: InputContentItemFile = com.volcengine.ark.runtime.model.responses.content.InputContentItemFile()
            inputContentItemFile.fileUrl = fileUrl
            inputContentItemFile.fileId = fileId
            inputContentItemFile.fileData = fileData
            inputContentItemFile.fileName = fileName
            return inputContentItemFile
        }

        companion object {
            fun anInputContentItemFile(): InputContentItemFileBuilder {
                return com.volcengine.ark.runtime.model.responses.content.InputContentItemFile.InputContentItemFileBuilder()
            }
        }
    }
}
