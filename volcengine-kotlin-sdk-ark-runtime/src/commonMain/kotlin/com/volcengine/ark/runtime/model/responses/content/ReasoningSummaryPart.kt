package com.volcengine.ark.runtime.model.responses.content

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ReasoningSummaryPart {
    @JsonProperty("type")
    var type: String? = null

    @JsonProperty("text")
    var text: String? = null

    @Override
    fun toString(): String? {
        return "ReasoningSummaryPart{" +
                "type='" + type + '\'' +
                ", text='" + text + '\'' +
                '}'
    }

    class Builder {
        private var type: String? = null
        private var text: String? = null

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun text(text: String?): Builder {
            this.text = text
            return this
        }

        fun build(): ReasoningSummaryPart {
            val reasoningSummaryPart: ReasoningSummaryPart = com.volcengine.ark.runtime.model.responses.content.ReasoningSummaryPart()
            reasoningSummaryPart.type = type
            reasoningSummaryPart.text = text
            return reasoningSummaryPart
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.content.ReasoningSummaryPart.Builder()
        }
    }
}