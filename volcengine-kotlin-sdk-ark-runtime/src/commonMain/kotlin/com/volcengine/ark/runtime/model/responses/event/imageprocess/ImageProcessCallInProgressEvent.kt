package com.volcengine.ark.runtime.model.responses.event.imageprocess
import kotlinx.serialization.Serializable

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants

@Serializable
class ImageProcessCallInProgressEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_IN_PROGRESS) {
}