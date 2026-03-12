package com.volcengine.ark.runtime.model.responses.content

import com.fasterxml.jackson.annotation.JsonProperty

class OutputContentItemText : OutputContentItem(ResponsesConstants.CONTENT_ITEM_TYPE_OUTPUT_TEXT) {
    @JsonProperty("text")
    var text: String? = null

    @JsonProperty("annotations")
    var annotations: List<Annotation?>? = null

    @Override
    fun toString(): String? {
        return "OutputContentItemText{" +
                "type='" + getType() + '\'' +
                ", text='" + text + '\'' +
                ", annotations=" + annotations +
                '}'
    }

    class Builder {
        private var text: String? = null
        private var annotations: List<Annotation?>? = null


        fun text(text: String?): Builder {
            this.text = text
            return this
        }

        fun annotations(annotations: List<Annotation?>?): Builder {
            this.annotations = annotations
            return this
        }

        fun build(): OutputContentItemText {
            val outputContentItemText: OutputContentItemText = com.volcengine.ark.runtime.model.responses.content.OutputContentItemText()
            outputContentItemText.text = text
            outputContentItemText.annotations = annotations
            return outputContentItemText
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.content.OutputContentItemText.Builder()
        }
    }
}