package com.volcengine.ark.runtime.model.responses.item.doubaoapp

import com.fasterxml.jackson.annotation.JsonProperty

class DoubaoAppCallBlockReasoningText : DoubaoAppCallBlock() {
    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("type")
    var type: String? = ResponsesConstants.DOBAO_APP_BLOCK_TYPE_REASONING_TEXT

    @JsonProperty("reasoning_text")
    var reasoningText: String? = null

    @JsonProperty("status")
    var status: String? = null

    @JsonProperty("parent_id")
    var parentId: String? = null

    class Builder {
        private var id: String? = null
        private var reasoningText: String? = null
        private var status: String? = null
        private var parentId: String? = null

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun reasoningText(reasoningText: String?): Builder {
            this.reasoningText = reasoningText
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

        fun build(): DoubaoAppCallBlockReasoningText {
            val reasoningText: DoubaoAppCallBlockReasoningText = com.volcengine.ark.runtime.model.responses.item.doubaoapp.DoubaoAppCallBlockReasoningText()
            reasoningText.id = id
            reasoningText.reasoningText = this.reasoningText
            reasoningText.status = status
            reasoningText.parentId = parentId
            return reasoningText
        }
    }

    @Override
    fun toString(): String? {
        return "DoubaoAppCallBlockReasoningText{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", reasoningText='" + reasoningText + '\'' +
                ", status='" + status + '\'' +
                ", parentId='" + parentId + '\'' +
                '}'
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.doubaoapp.DoubaoAppCallBlockReasoningText.Builder()
        }
    }
}
