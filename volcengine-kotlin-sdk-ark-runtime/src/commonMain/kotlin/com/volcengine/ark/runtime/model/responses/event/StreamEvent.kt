package com.volcengine.ark.runtime.model.responses.event
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(
    [JsonSubTypes.Type(value = ResponseCreatedEvent::class, name = ResponsesConstants.EVENT_TYPE_RESPONSE_CREATED), JsonSubTypes.Type(
        value = ResponseInProgressEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_IN_PROGRESS
    ), JsonSubTypes.Type(value = ResponseCompletedEvent::class, name = ResponsesConstants.EVENT_TYPE_RESPONSE_COMPLETED), JsonSubTypes.Type(
        value = ResponseFailedEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_FAILED
    ), JsonSubTypes.Type(value = ResponseInCompleteEvent::class, name = ResponsesConstants.EVENT_TYPE_RESPONSE_INCOMPLETE), JsonSubTypes.Type(
        value = OutputItemAddedEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_ITEM_ADDED
    ), JsonSubTypes.Type(value = OutputItemDoneEvent::class, name = ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_ITEM_DONE), JsonSubTypes.Type(
        value = ContentPartAddedEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_CONTENT_PART_ADDED
    ), JsonSubTypes.Type(value = ContentPartDoneEvent::class, name = ResponsesConstants.EVENT_TYPE_RESPONSE_CONTENT_PART_DONE), JsonSubTypes.Type(
        value = OutputTextDeltaEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_TEXT_DELTA
    ), JsonSubTypes.Type(value = OutputTextDoneEvent::class, name = ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_TEXT_DONE), JsonSubTypes.Type(
        value = AnnotationAddedEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_OUTPUT_TEXT_ANNOTATION_ADDED
    ), JsonSubTypes.Type(
        value = ReasoningSummaryTextDeltaEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_REASONING_SUMMARY_TEXT_DELTA
    ), JsonSubTypes.Type(
        value = ReasoningSummaryTextDoneEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_REASONING_SUMMARY_TEXT_DONE
    ), JsonSubTypes.Type(
        value = ReasoningSummaryPartAddedEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_REASONING_SUMMARY_PART_ADDED
    ), JsonSubTypes.Type(
        value = ReasoningSummaryPartDoneEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_REASONING_SUMMARY_PART_DONE
    ), JsonSubTypes.Type(
        value = FunctionCallArgumentsDeltaEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_FUNCTION_CALL_ARGUMENTS_DELTA
    ), JsonSubTypes.Type(value = FunctionCallArgumentsDoneEvent::class, name = ResponsesConstants.EVENT_TYPE_RESPONSE_FUNCTION_CALL_ARGUMENTS_DONE), JsonSubTypes.Type(
        value = ErrorEvent::class,
        name = ResponsesConstants.EVENT_TYPE_ERROR
    ), JsonSubTypes.Type(
        value = WebSearchCallInProgressEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_WEB_SEARCH_CALL_IN_PROGRESS
    ), JsonSubTypes.Type(
        value = WebSearchCallSearchingEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_WEB_SEARCH_CALL_SEARCHING
    ), JsonSubTypes.Type(
        value = WebSearchCallCompletedEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_WEB_SEARCH_CALL_COMPLETED
    ), JsonSubTypes.Type(
        value = ImageProcessCallInProgressEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_IN_PROGRESS
    ), JsonSubTypes.Type(
        value = ImageProcessCallProcessingEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_PROGRESSING
    ), JsonSubTypes.Type(
        value = ImageProcessCallCompletedEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_COMPLETED
    ), JsonSubTypes.Type(
        value = ImageProcessCallFailedEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_FAILED
    ), JsonSubTypes.Type(
        value = MCPListToolInProgressEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_LIST_TOOLS_IN_PROGRESS
    ), JsonSubTypes.Type(value = MCPListToolCompletedEvent::class, name = ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_LIST_TOOLS_COMPLETED), JsonSubTypes.Type(
        value = MCPCallInProgressEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_CALL_IN_PROGRESS
    ), JsonSubTypes.Type(value = MCPCallFailedEvent::class, name = ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_CALL_FAILED), JsonSubTypes.Type(
        value = MCPCallArgumentsDeltaEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_CALL_ARGUMENTS_DELTA
    ), JsonSubTypes.Type(value = MCPCallArgumentsDoneEvent::class, name = ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_CALL_ARGUMENTS_DONE), JsonSubTypes.Type(
        value = MCPCallCompletedEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_CALL_COMPLETED
    ), JsonSubTypes.Type(value = MCPCallFailedEvent::class, name = ResponsesConstants.EVENT_TYPE_RESPONSE_MCP_CALL_FAILED), JsonSubTypes.Type(
        value = DoubaoAppCallInProgressEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_IN_PROGRESS
    ), JsonSubTypes.Type(
        value = DoubaoAppCallCompletedEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_COMPLETED
    ), JsonSubTypes.Type(value = DoubaoAppCallFailedEvent::class, name = ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_FAILED), JsonSubTypes.Type(
        value = DoubaoAppCallBlockAddedEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_BLOCK_ADDED
    ), JsonSubTypes.Type(
        value = DoubaoAppCallBlockDoneEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_BLOCK_DONE
    ), JsonSubTypes.Type(
        value = DoubaoAppCallOutputTextDeltaEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_OUTPUT_TEXT_DELTA
    ), JsonSubTypes.Type(
        value = DoubaoAppCallOutputTextDoneEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_OUTPUT_TEXT_DONE
    ), JsonSubTypes.Type(
        value = DoubaoAppCallReasoningTextDeltaEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_TEXT_DELTA
    ), JsonSubTypes.Type(
        value = DoubaoAppCallReasoningTextDoneEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_TEXT_DONE
    ), JsonSubTypes.Type(
        value = DoubaoAppCallSearchInProgressEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_SEARCH_IN_PROGRESS
    ), JsonSubTypes.Type(
        value = DoubaoAppCallSearchSearchingEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_SEARCH_SEARCHING
    ), JsonSubTypes.Type(
        value = DoubaoAppCallSearchCompletedEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_SEARCH_COMPLETED
    ), JsonSubTypes.Type(value = DoubaoAppCallReasoningSearchInProgressEvent::class, name = ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_SEARCH_IN_PROGRESS), JsonSubTypes.Type(
        value = DoubaoAppCallReasoningSearchSearchingEvent::class,
        name = ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_SEARCH_SEARCHING
    ), JsonSubTypes.Type(value = DoubaoAppCallReasoningSearchCompletedEvent::class, name = ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_SEARCH_COMPLETED)]
)
@Serializable
abstract class StreamEvent(@field:JsonProperty("type") var type: String?) {
    @SerialName("sequence_number")
    var sequenceNumber: Long? = null

}