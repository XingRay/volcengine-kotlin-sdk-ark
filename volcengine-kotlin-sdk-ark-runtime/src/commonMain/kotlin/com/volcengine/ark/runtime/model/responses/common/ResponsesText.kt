package com.volcengine.ark.runtime.model.responses.common

import com.fasterxml.jackson.annotation.JsonProperty

class ResponsesText {
    @JsonProperty("format")
    private var format: ResponsesTextFormat? = null

    fun getFormat(): ResponsesTextFormat? {
        return format
    }

    fun setFormat(format: ResponsesTextFormat?) {
        this.format = format
    }

    @Override
    fun toString(): String? {
        return "ResponsesText{" +
                "format=" + format +
                '}'
    }

    class Builder {
        private var format: ResponsesTextFormat? = null

        fun format(format: ResponsesTextFormat?): Builder {
            this.format = format
            return this
        }

        fun build(): ResponsesText {
            val responsesText: ResponsesText = com.volcengine.ark.runtime.model.responses.common.ResponsesText()
            responsesText.setFormat(format)
            return responsesText
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.common.ResponsesText.Builder()
        }
    }
}