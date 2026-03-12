package com.volcengine.ark.runtime.model.responses.event.imageprocess

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import com.volcengine.ark.runtime.model.responses.event.StreamEvent
import com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessAction
import com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessArguments
import com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessError
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_FAILED)
data class ImageProcessCallFailedEvent(
    @SerialName("type")
    override val type: String = ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_FAILED,
    @SerialName("sequence_number")
    override val sequenceNumber: Long? = null,
    @SerialName("output_index")
    val outputIndex: Long? = null,
    @SerialName("item_id")
    val itemId: String? = null,
    @SerialName("action")
    val action: ImageProcessAction? = null,
    @SerialName("arguments")
    val arguments: ImageProcessArguments? = null,
    @SerialName("error")
    val error: ImageProcessError? = null
) : StreamEvent(type) {
    constructor() : this(ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_FAILED)
}