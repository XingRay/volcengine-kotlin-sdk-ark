package com.volcengine.ark.runtime.model.responses.item.doubaoapp

import com.fasterxml.jackson.annotation.JsonProperty

class DoubaoAppCallBlockOutputText : DoubaoAppCallBlock() {
    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("type")
    var type: String? = ResponsesConstants.DOBAO_APP_BLOCK_TYPE_OUTPUT_TEXT

    @JsonProperty("text")
    var text: String? = null

    @JsonProperty("status")
    var status: String? = null

    @JsonProperty("parent_id")
    var parentId: String? = null

    class Builder {
        private var id: String? = null
        private var text: String? = null
        private var status: String? = null
        private var parentId: String? = null

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun text(text: String?): Builder {
            this.text = text
            return this
        }

        fun status(status: String?): Builder {
            this.status = status
            return this
        }

        fun parentId(parentId: String?): Builder {
            this.parentId = parentId
            return this
        }

        fun build(): DoubaoAppCallBlockOutputText {
            val outputText: DoubaoAppCallBlockOutputText = com.volcengine.ark.runtime.model.responses.item.doubaoapp.DoubaoAppCallBlockOutputText()
            outputText.id = id
            outputText.text = text
            outputText.status = status
            outputText.parentId = parentId
            return outputText
        }
    }

    @Override
    fun toString(): String? {
        return "DoubaoAppCallBlockOutputText{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", status='" + status + '\'' +
                ", parentId='" + parentId + '\'' +
                '}'
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.doubaoapp.DoubaoAppCallBlockOutputText.Builder()
        }
    }
}
