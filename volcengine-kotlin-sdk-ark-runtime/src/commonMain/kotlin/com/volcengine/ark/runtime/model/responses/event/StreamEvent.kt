package com.volcengine.ark.runtime.model.responses.event

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import com.volcengine.ark.runtime.model.responses.event.contentpart.ContentPartAddedEvent
import com.volcengine.ark.runtime.model.responses.event.contentpart.ContentPartDoneEvent
import com.volcengine.ark.runtime.model.responses.event.doubaoapp.*
import com.volcengine.ark.runtime.model.responses.event.functioncall.FunctionCallArgumentsDeltaEvent
import com.volcengine.ark.runtime.model.responses.event.functioncall.FunctionCallArgumentsDoneEvent
import com.volcengine.ark.runtime.model.responses.event.imageprocess.ImageProcessCallCompletedEvent
import com.volcengine.ark.runtime.model.responses.event.imageprocess.ImageProcessCallFailedEvent
import com.volcengine.ark.runtime.model.responses.event.imageprocess.ImageProcessCallInProgressEvent
import com.volcengine.ark.runtime.model.responses.event.imageprocess.ImageProcessCallProcessingEvent
import com.volcengine.ark.runtime.model.responses.event.mcp.*
import com.volcengine.ark.runtime.model.responses.event.outputitem.OutputItemAddedEvent
import com.volcengine.ark.runtime.model.responses.event.outputitem.OutputItemDoneEvent
import com.volcengine.ark.runtime.model.responses.event.outputtext.AnnotationAddedEvent
import com.volcengine.ark.runtime.model.responses.event.outputtext.OutputTextDeltaEvent
import com.volcengine.ark.runtime.model.responses.event.outputtext.OutputTextDoneEvent
import com.volcengine.ark.runtime.model.responses.event.reasoningsummary.ReasoningSummaryPartAddedEvent
import com.volcengine.ark.runtime.model.responses.event.reasoningsummary.ReasoningSummaryPartDoneEvent
import com.volcengine.ark.runtime.model.responses.event.reasoningsummary.ReasoningSummaryTextDeltaEvent
import com.volcengine.ark.runtime.model.responses.event.reasoningsummary.ReasoningSummaryTextDoneEvent
import com.volcengine.ark.runtime.model.responses.event.response.*
import com.volcengine.ark.runtime.model.responses.event.websearch.WebSearchCallCompletedEvent
import com.volcengine.ark.runtime.model.responses.event.websearch.WebSearchCallInProgressEvent
import com.volcengine.ark.runtime.model.responses.event.websearch.WebSearchCallSearchingEvent
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable(with = StreamEventPolymorphicSerializer::class)
abstract class StreamEvent(
    open val type: String
) {
    constructor() : this("")

    @SerialName("sequence_number")
    open val sequenceNumber: Long? = null
}

object StreamEventPolymorphicSerializer : JsonContentPolymorphicSerializer<StreamEvent>(StreamEvent::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out StreamEvent> {
        val type = element.jsonObject["type"]?.jsonPrimitive?.content
            ?: throw SerializationException("缺失 type 字段")

        return when (type) {
            ResponsesConstants.EVENT_TYPE_RESPONSE_CREATED -> ResponseCreatedEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_IN_PROGRESS -> ResponseInProgressEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_COMPLETED -> ResponseCompletedEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_FAILED -> ResponseFailedEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_INCOMPLETE -> ResponseInCompleteEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_ITEM_ADDED -> OutputItemAddedEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_ITEM_DONE -> OutputItemDoneEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_CONTENT_PART_ADDED -> ContentPartAddedEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_CONTENT_PART_DONE -> ContentPartDoneEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_TEXT_DELTA -> OutputTextDeltaEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_TEXT_DONE -> OutputTextDoneEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_TEXT_ANNOTATION_ADDED -> AnnotationAddedEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_REASONING_SUMMARY_TEXT_DELTA -> ReasoningSummaryTextDeltaEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_REASONING_SUMMARY_TEXT_DONE -> ReasoningSummaryTextDoneEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_REASONING_SUMMARY_PART_ADDED -> ReasoningSummaryPartAddedEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_REASONING_SUMMARY_PART_DONE -> ReasoningSummaryPartDoneEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_FUNCTION_CALL_ARGUMENTS_DELTA -> FunctionCallArgumentsDeltaEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_FUNCTION_CALL_ARGUMENTS_DONE -> FunctionCallArgumentsDoneEvent.serializer()
            ResponsesConstants.EVENT_TYPE_ERROR -> ErrorEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_WEB_SEARCH_CALL_IN_PROGRESS -> WebSearchCallInProgressEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_WEB_SEARCH_CALL_SEARCHING -> WebSearchCallSearchingEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_WEB_SEARCH_CALL_COMPLETED -> WebSearchCallCompletedEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_IN_PROGRESS -> ImageProcessCallInProgressEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_PROGRESSING -> ImageProcessCallProcessingEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_COMPLETED -> ImageProcessCallCompletedEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_FAILED -> ImageProcessCallFailedEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_LIST_TOOLS_IN_PROGRESS -> MCPListToolInProgressEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_LIST_TOOLS_COMPLETED -> MCPListToolCompletedEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_CALL_IN_PROGRESS -> MCPCallInProgressEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_CALL_FAILED -> MCPCallFailedEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_CALL_ARGUMENTS_DELTA -> MCPCallArgumentsDeltaEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_CALL_ARGUMENTS_DONE -> MCPCallArgumentsDoneEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_CALL_COMPLETED -> MCPCallCompletedEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_IN_PROGRESS -> DoubaoAppCallInProgressEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_COMPLETED -> DoubaoAppCallCompletedEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_FAILED -> DoubaoAppCallFailedEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_BLOCK_ADDED -> DoubaoAppCallBlockAddedEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_BLOCK_DONE -> DoubaoAppCallBlockDoneEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_OUTPUT_TEXT_DELTA -> DoubaoAppCallOutputTextDeltaEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_OUTPUT_TEXT_DONE -> DoubaoAppCallOutputTextDoneEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_TEXT_DELTA -> DoubaoAppCallReasoningTextDeltaEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_TEXT_DONE -> DoubaoAppCallReasoningTextDoneEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_SEARCH_IN_PROGRESS -> DoubaoAppCallSearchInProgressEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_SEARCH_SEARCHING -> DoubaoAppCallSearchSearchingEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_SEARCH_COMPLETED -> DoubaoAppCallSearchCompletedEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_SEARCH_IN_PROGRESS -> DoubaoAppCallReasoningSearchInProgressEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_SEARCH_SEARCHING -> DoubaoAppCallReasoningSearchSearchingEvent.serializer()
            ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_SEARCH_COMPLETED -> DoubaoAppCallReasoningSearchCompletedEvent.serializer()
            else -> throw SerializationException("不支持的事件类型: $type")
        }
    }
}