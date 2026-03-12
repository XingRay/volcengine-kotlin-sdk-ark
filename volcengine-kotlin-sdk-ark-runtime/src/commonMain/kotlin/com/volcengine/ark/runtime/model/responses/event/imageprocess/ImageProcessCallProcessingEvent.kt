package com.volcengine.ark.runtime.model.responses.event.imageprocess

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import com.volcengine.ark.runtime.model.responses.event.StreamEvent
import com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessAction
import com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessArguments
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_PROGRESSING)
data class ImageProcessCallProcessingEvent(
    @SerialName("type")
    override val type: String = ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_PROGRESSING,
    @SerialName("sequence_number")
    override val sequenceNumber: Long? = null,
    @SerialName("output_index")
    val outputIndex: Long? = null,
    @SerialName("item_id")
    val itemId: String? = null,
    @SerialName("action")
    val action: ImageProcessAction? = null,
    @SerialName("arguments")
    val arguments: ImageProcessArguments? = null
) : StreamEvent(type) {
    constructor() : this(ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_PROGRESSING)
}