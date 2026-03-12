package com.volcengine.ark.runtime.model.responses.item.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class DoubaoAppCallBlockReasoningText : DoubaoAppCallBlock() {
    @SerialName("id")
    var id: String? = null

    @SerialName("type")
    var type: String? = ResponsesConstants.DOBAO_APP_BLOCK_TYPE_REASONING_TEXT

    @SerialName("reasoning_text")
    var reasoningText: String? = null

    @SerialName("status")
    var status: String? = null

    @SerialName("parent_id")
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


    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.doubaoapp.DoubaoAppCallBlockReasoningText.Builder()
        }
    }
}
