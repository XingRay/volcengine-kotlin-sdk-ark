package com.volcengine.ark.runtime.model.completion.chat

import com.fasterxml.jackson.annotation.JsonAlias

@JsonIgnoreProperties(ignoreUnknown = true)
class ChatCompletionResult {
    /**
     * Unique id assigned to this chat completion.
     */
    var id: String? = null

    /**
     * The type of object returned, should be "chat.completion"
     */
    var `object`: String? = null
        set(object) {
            field = this.`object`
        }

    /**
     * The creation time in epoch seconds.
     */
    var created: Long = 0

    /**
     * The GPT model used.
     */
    var model: String? = null

    /**
     * Specifies the latency tier to use for processing the request.
     * 
     * This parameter is relevant for customers subscribed to the scale tier service:
     * 
     * - If set to 'auto', and the endpoint is Scale tier enabled, the system will
     * utilize scale tier credits until they are exhausted.
     * - If set to 'auto', and the endpoint is not Scale tier enabled, the request will
     * be processed using the default service tier with a lower uptime SLA and no
     * latency guarentee.
     * - If set to 'default', the request will be processed using the default service
     * tier with a lower uptime SLA and no latency guarentee.
     * - When not set, the default behavior is 'auto'.
     * 
     * When this parameter is set, the response body will include the `service_tier`
     * utilized.
     */
    @JsonAlias("service_tier")
    var serviceTier: String? = null

    /**
     * A list of all generated completions.
     */
    var choices: List<ChatCompletionChoice?>? = null

    /**
     * The API usage for this request.
     */
    var usage: Usage? = null

    fun getChoices(): List<ChatCompletionChoice?>? {
        return choices
    }

    fun setChoices(choices: List<ChatCompletionChoice?>?) {
        this.choices = choices
    }

    fun getUsage(): Usage? {
        return usage
    }

    fun setUsage(usage: Usage?) {
        this.usage = usage
    }

    @Override
    fun toString(): String? {
        return "ChatCompletionResult{" +
                "id='" + id + '\'' +
                ", object='" + this.`object` + '\'' +
                ", created=" + created +
                ", model='" + model + '\'' +
                ", service_tier='" + serviceTier + '\'' +
                ", choices=" + choices +
                ", usage=" + usage +
                '}'
    }
}
