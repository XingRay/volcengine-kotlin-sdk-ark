package com.volcengine.ark.runtime.model.completion.chat

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ChatCompletionChoiceLogprob {
    var content: List<ChatCompletionChoiceLogprobContent?>? = null

    fun getContent(): List<ChatCompletionChoiceLogprobContent?>? {
        return content
    }

    fun setContent(content: List<ChatCompletionChoiceLogprobContent?>?) {
        this.content = content
    }

    @Override
    fun toString(): String? {
        return "ChatCompletionChoiceLogprob{" +
                "content=" + content +
                '}'
    }
}
