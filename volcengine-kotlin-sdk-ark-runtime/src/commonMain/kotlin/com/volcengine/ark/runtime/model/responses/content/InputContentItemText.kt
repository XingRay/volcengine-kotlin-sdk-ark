package com.volcengine.ark.runtime.model.responses.content

import com.fasterxml.jackson.annotation.JsonProperty

class InputContentItemText : InputContentItem(ResponsesConstants.CONTENT_ITEM_TYPE_INPUT_TEXT) {
    @JsonProperty("text")
    var text: String? = null

    @Override
    fun toString(): String? {
        return "InputContentItemText {\n{" +
                "type='" + getType() + '\'' +
                ", text='" + text + '\'' +
                '}'
    }

    class Builder {
        private var text: String? = null

        fun text(text: String?): Builder {
            this.text = text
            return this
        }

        fun build(): InputContentItemText {
            val responsesContentItemText: InputContentItemText = com.volcengine.ark.runtime.model.responses.content.InputContentItemText()
            responsesContentItemText.text = text
            return responsesContentItemText
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.content.InputContentItemText.Builder()
        }
    }
}