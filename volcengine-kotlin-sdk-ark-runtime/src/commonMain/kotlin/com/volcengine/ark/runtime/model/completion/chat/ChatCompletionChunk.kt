package com.volcengine.ark.runtime.model.completion.chat
import com.volcengine.ark.runtime.model.Usage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
open class ChatCompletionChunk(
    /**
     * Unique id assigned to this chat completion.
     */
    val id: String? = null,

    /**
     * The type of object returned, should be "chat.completion.chunk"
     */
    val `object`: String? = null,

    /**
     * The creation time in epoch seconds.
     */
    val created: Long = 0,

    /**
     * The model used.
     */
    val model: String? = null,

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
    @SerialName("service_tier")
    val serviceTier: String? = null,

    /**
     * A list of all generated completions.
     */
    val choices: List<ChatCompletionChoice>? = null,

    /**
     * The API usage for this request.
     */
    val usage: Usage? = null
)