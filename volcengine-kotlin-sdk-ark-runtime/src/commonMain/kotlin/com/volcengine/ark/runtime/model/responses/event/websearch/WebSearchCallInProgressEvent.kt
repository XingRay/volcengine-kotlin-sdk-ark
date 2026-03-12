package com.volcengine.ark.runtime.model.responses.event.websearch
import kotlinx.serialization.Serializable

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants

@Serializable
class WebSearchCallInProgressEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_WEB_SEARCH_CALL_IN_PROGRESS) {
}