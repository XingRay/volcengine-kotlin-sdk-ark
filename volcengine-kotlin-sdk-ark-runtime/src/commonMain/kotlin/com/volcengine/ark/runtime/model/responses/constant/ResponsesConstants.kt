package com.volcengine.ark.runtime.model.responses.constant

/**
 * Constants for the Responses API.
 */
object ResponsesConstants {
    // ResponseImageProcessType.Enum
    val IMAGE_PROCESS_TYPE_POINT: String = "point"
    val IMAGE_PROCESS_TYPE_GROUNDING: String = "grounding"
    val IMAGE_PROCESS_TYPE_ROTATE: String = "rotate"
    val IMAGE_PROCESS_TYPE_ZOOM: String = "zoom"

    // ItemType.Enum
    val ITEM_TYPE_MESSAGE: String = "message"
    val ITEM_TYPE_FUNCTION_CALL: String = "function_call"
    val ITEM_TYPE_FUNCTION_CALL_OUTPUT: String = "function_call_output"
    val ITEM_TYPE_REASONING: String = "reasoning"
    val ITEM_TYPE_ITEM_REFERENCE: String = "item_reference"
    val ITEM_TYPE_WEB_SEARCH_CALL: String = "web_search_call"
    val ITEM_TYPE_IMAGE_PROCESS: String = "image_process"
    val ITEM_TYPE_MCP_APPROVAL_REQUEST: String = "mcp_approval_request"
    val ITEM_TYPE_MCP_APPROVAL_RESPONSE: String = "mcp_approval_response"
    val ITEM_TYPE_MCP_LIST_TOOLS: String = "mcp_list_tools"
    val ITEM_TYPE_MCP_CALL: String = "mcp_call"
    val ITEM_TYPE_KNOWLEDGE_SEARCH_CALL: String = "knowledge_search_call"
    val ITEM_TYPE_DOUBAO_APP_CALL: String = "doubao_app_call"

    // MessageRole.Enum
    val MESSAGE_ROLE_USER: String = "user"
    val MESSAGE_ROLE_SYSTEM: String = "system"
    val MESSAGE_ROLE_DEVELOPER: String = "developer"
    val MESSAGE_ROLE_ASSISTANT: String = "assistant"

    // ContentItemImageDetail.Enum
    val IMAGE_DETAIL_AUTO: String = "auto"
    val IMAGE_DETAIL_HIGH: String = "high"
    val IMAGE_DETAIL_LOW: String = "low"

    // ContentItemType.Enum
    val CONTENT_ITEM_TYPE_INPUT_TEXT: String = "input_text"
    val CONTENT_ITEM_TYPE_INPUT_IMAGE: String = "input_image"
    val CONTENT_ITEM_TYPE_INPUT_VIDEO: String = "input_video"
    val CONTENT_ITEM_TYPE_INPUT_FILE: String = "input_file"
    val CONTENT_ITEM_TYPE_OUTPUT_TEXT: String = "output_text"
    val CONTENT_ITEM_TYPE_SUMMARY_TEXT: String = "summary_text"

    // ItemStatus.Enum
    val ITEM_STATUS_IN_PROGRESS: String = "in_progress"
    val ITEM_STATUS_COMPLETED: String = "completed"
    val ITEM_STATUS_INCOMPLETE: String = "incomplete"
    val ITEM_STATUS_SEARCHING: String = "searching"
    val ITEM_STATUS_FAILED: String = "failed"

    // ResponseStatus.Enum
    val RESPONSE_STATUS_IN_PROGRESS: String = "in_progress"
    val RESPONSE_STATUS_COMPLETED: String = "completed"
    val RESPONSE_STATUS_INCOMPLETE: String = "incomplete"
    val RESPONSE_STATUS_FAILED: String = "failed"

    // ThinkingType.Enum
    val THINKING_TYPE_AUTO: String = "auto"
    val THINKING_TYPE_DISABLED: String = "disabled"
    val THINKING_TYPE_ENABLED: String = "enabled"

    // TextType.Enum
    val TEXT_TYPE_TEXT: String = "text"
    val TEXT_TYPE_JSON_OBJECT: String = "json_object"
    val TEXT_TYPE_JSON_SCHEMA: String = "json_schema"

    // ToolChoiceMode.Enum
    val TOOL_CHOICE_MODE_AUTO: String = "auto"
    val TOOL_CHOICE_MODE_NONE: String = "none"
    val TOOL_CHOICE_MODE_REQUIRED: String = "required"

    // ToolType.Enum
    val TOOL_TYPE_FUNCTION: String = "function"
    val TOOL_TYPE_WEB_SEARCH: String = "web_search"
    val TOOL_TYPE_IMAGE_PROCESS: String = "image_process"
    val TOOL_TYPE_MCP: String = "mcp"
    val TOOL_TYPE_DOUBAO_APP: String = "doubao_app"

    // UserLocationType.Enum
    val USER_LOCATION_TYPE_APPROXIMATE: String = "approximate"

    // ObjectType.Enum
    val OBJECT_TYPE_RESPONSE: String = "response"
    val OBJECT_TYPE_LIST: String = "list"

    // Response lifecycle events
    val EVENT_TYPE_RESPONSE_CREATED: String = "response.created"
    val EVENT_TYPE_RESPONSE_IN_PROGRESS: String = "response.in_progress"
    val EVENT_TYPE_RESPONSE_COMPLETED: String = "response.completed"
    val EVENT_TYPE_RESPONSE_FAILED: String = "response.failed"
    val EVENT_TYPE_RESPONSE_INCOMPLETE: String = "response.incomplete"

    // Output item events
    val EVENT_TYPE_RESPONSE_OUTPUT_ITEM_ADDED: String = "response.output_item.added"
    val EVENT_TYPE_RESPONSE_OUTPUT_ITEM_DONE: String = "response.output_item.done"

    // Content part events
    val EVENT_TYPE_RESPONSE_CONTENT_PART_ADDED: String = "response.content_part.added"
    val EVENT_TYPE_RESPONSE_CONTENT_PART_DONE: String = "response.content_part.done"

    // Output text events
    val EVENT_TYPE_RESPONSE_OUTPUT_TEXT_DELTA: String = "response.output_text.delta"
    val EVENT_TYPE_RESPONSE_OUTPUT_TEXT_DONE: String = "response.output_text.done"
    val EVENT_TYPE_RESPONSE_OUTPUT_TEXT_ANNOTATION_ADDED: String = "response.output_text.annotation.added"

    // Reasoning summary events
    val EVENT_TYPE_RESPONSE_REASONING_SUMMARY_TEXT_DELTA: String = "response.reasoning_summary_text.delta"
    val EVENT_TYPE_RESPONSE_REASONING_SUMMARY_TEXT_DONE: String = "response.reasoning_summary_text.done"
    val EVENT_TYPE_RESPONSE_REASONING_SUMMARY_PART_ADDED: String = "response.reasoning_summary_part.added"
    val EVENT_TYPE_RESPONSE_REASONING_SUMMARY_PART_DONE: String = "response.reasoning_summary_part.done"

    // Function call events
    val EVENT_TYPE_RESPONSE_FUNCTION_CALL_ARGUMENTS_DELTA: String = "response.function_call_arguments.delta"
    val EVENT_TYPE_RESPONSE_FUNCTION_CALL_ARGUMENTS_DONE: String = "response.function_call_arguments.done"

    // Error events
    val EVENT_TYPE_ERROR: String = "error"

    // Web search events
    val EVENT_TYPE_RESPONSE_WEB_SEARCH_CALL_IN_PROGRESS: String = "response.web_search_call.in_progress"
    val EVENT_TYPE_RESPONSE_WEB_SEARCH_CALL_SEARCHING: String = "response.web_search_call.searching"
    val EVENT_TYPE_RESPONSE_WEB_SEARCH_CALL_COMPLETED: String = "response.web_search_call.completed"

    // Image process events
    val EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_IN_PROGRESS: String = "response.image_process_call.in_progress"
    val EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_PROGRESSING: String = "response.image_process_call.progressing"
    val EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_COMPLETED: String = "response.image_process_call.completed"
    val EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_FAILED: String = "response.image_process_call.failed"

    // MCP events
    val EVENT_TYPE_RESPONSE_MCP_LIST_TOOLS_IN_PROGRESS: String = "response.mcp_list_tools.in_progress"
    val EVENT_TYPE_RESPONSE_MCP_LIST_TOOLS_COMPLETED: String = "response.mcp_list_tools.completed"
    val EVENT_TYPE_RESPONSE_MCP_CALL_IN_PROGRESS: String = "response.mcp_call.in_progress"
    val EVENT_TYPE_RESPONSE_MCP_CALL_ARGUMENTS_DELTA: String = "response.mcp_call_arguments.delta"
    val EVENT_TYPE_RESPONSE_MCP_CALL_ARGUMENTS_DONE: String = "response.mcp_call_arguments.done"
    val EVENT_TYPE_RESPONSE_MCP_CALL_COMPLETED: String = "response.mcp_call.completed"
    val EVENT_TYPE_RESPONSE_MCP_CALL_FAILED: String = "response.mcp_call.failed"

    // Doubao app events
    val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_IN_PROGRESS: String = "response.doubao_app_call.in_progress"
    val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_COMPLETED: String = "response.doubao_app_call.completed"
    val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_FAILED: String = "response.doubao_app_call.failed"
    val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_OUTPUT_TEXT_DELTA: String = "response.doubao_app_call_output_text.delta"
    val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_OUTPUT_TEXT_DONE: String = "response.doubao_app_call_output_text.done"
    val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_SEARCH_IN_PROGRESS: String = "response.doubao_app_call_search.in_progress"
    val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_SEARCH_SEARCHING: String = "response.doubao_app_call_search.searching"
    val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_SEARCH_COMPLETED: String = "response.doubao_app_call_search.completed"
    val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_TEXT_DELTA: String = "response.doubao_app_call_reasoning_text.delta"
    val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_TEXT_DONE: String = "response.doubao_app_call_reasoning_text.done"
    val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_SEARCH_IN_PROGRESS: String = "response.doubao_app_call_reasoning_search.in_progress"
    val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_SEARCH_SEARCHING: String = "response.doubao_app_call_reasoning_search.searching"
    val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_SEARCH_COMPLETED: String = "response.doubao_app_call_reasoning_search.completed"
    val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_BLOCK_ADDED: String = "response.doubao_app_call_block.added"
    val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_BLOCK_DONE: String = "response.doubao_app_call_block.done"

    // IncludeType.Enum
    val INCLUDE_TYPE_IMAGE_URL: String = "message.input_image.image_url"

    // CacheType.Enum
    val CACHE_TYPE_DISABLED: String = "disabled"
    val CACHE_TYPE_ENABLED: String = "enabled"

    // MCP Enum
    val MCP_APPROVAL_MODE_ALWAYS: String = "always"
    val MCP_APPROVAL_MODE_NEVER: String = "never"

    // DoubaoAppBlockType.Enum
    val DOBAO_APP_BLOCK_TYPE_OUTPUT_TEXT: String = "output_text"
    val DOBAO_APP_BLOCK_TYPE_REASONING_TEXT: String = "reasoning_text"
    val DOBAO_APP_BLOCK_TYPE_SEARCH: String = "search"
    val DOBAO_APP_BLOCK_TYPE_REASONING_SEARCH: String = "reasoning_search"

    // ResponseDoubaoAppFeatureType.Enum
    val RESPONSE_DOBAO_APP_FEATURE_TYPE_UNSPECIFIED: String = "unspecified"
    val RESPONSE_DOBAO_APP_FEATURE_TYPE_ENABLED: String = "enabled"
    val RESPONSE_DOBAO_APP_FEATURE_TYPE_DISABLED: String = "disabled"
}