package com.volcengine.ark.runtime.model.responses.constant

/**
 * Constants for the Responses API.
 */
object ResponsesConstants {
    // ResponseImageProcessType.Enum
    const val IMAGE_PROCESS_TYPE_POINT: String = "point"
    const val IMAGE_PROCESS_TYPE_GROUNDING: String = "grounding"
    const val IMAGE_PROCESS_TYPE_ROTATE: String = "rotate"
    const val IMAGE_PROCESS_TYPE_ZOOM: String = "zoom"

    // ItemType.Enum
    const val ITEM_TYPE_MESSAGE: String = "message"
    const val ITEM_TYPE_FUNCTION_CALL: String = "function_call"
    const val ITEM_TYPE_FUNCTION_CALL_OUTPUT: String = "function_call_output"
    const val ITEM_TYPE_REASONING: String = "reasoning"
    const val ITEM_TYPE_ITEM_REFERENCE: String = "item_reference"
    const val ITEM_TYPE_WEB_SEARCH_CALL: String = "web_search_call"
    const val ITEM_TYPE_IMAGE_PROCESS: String = "image_process"
    const val ITEM_TYPE_MCP_APPROVAL_REQUEST: String = "mcp_approval_request"
    const val ITEM_TYPE_MCP_APPROVAL_RESPONSE: String = "mcp_approval_response"
    const val ITEM_TYPE_MCP_LIST_TOOLS: String = "mcp_list_tools"
    const val ITEM_TYPE_MCP_CALL: String = "mcp_call"
    const val ITEM_TYPE_KNOWLEDGE_SEARCH_CALL: String = "knowledge_search_call"
    const val ITEM_TYPE_DOUBAO_APP_CALL: String = "doubao_app_call"

    // MessageRole.Enum
    const val MESSAGE_ROLE_USER: String = "user"
    const val MESSAGE_ROLE_SYSTEM: String = "system"
    const val MESSAGE_ROLE_DEVELOPER: String = "developer"
    const val MESSAGE_ROLE_ASSISTANT: String = "assistant"

    // ContentItemImageDetail.Enum
    const val IMAGE_DETAIL_AUTO: String = "auto"
    const val IMAGE_DETAIL_HIGH: String = "high"
    const val IMAGE_DETAIL_LOW: String = "low"

    // ContentItemType.Enum
    const val CONTENT_ITEM_TYPE_INPUT_TEXT: String = "input_text"
    const val CONTENT_ITEM_TYPE_INPUT_IMAGE: String = "input_image"
    const val CONTENT_ITEM_TYPE_INPUT_VIDEO: String = "input_video"
    const val CONTENT_ITEM_TYPE_INPUT_FILE: String = "input_file"
    const val CONTENT_ITEM_TYPE_OUTPUT_TEXT: String = "output_text"
    const val CONTENT_ITEM_TYPE_SUMMARY_TEXT: String = "summary_text"

    // ItemStatus.Enum
    const val ITEM_STATUS_IN_PROGRESS: String = "in_progress"
    const val ITEM_STATUS_COMPLETED: String = "completed"
    const val ITEM_STATUS_INCOMPLETE: String = "incomplete"
    const val ITEM_STATUS_SEARCHING: String = "searching"
    const val ITEM_STATUS_FAILED: String = "failed"

    // ResponseStatus.Enum
    const val RESPONSE_STATUS_IN_PROGRESS: String = "in_progress"
    const val RESPONSE_STATUS_COMPLETED: String = "completed"
    const val RESPONSE_STATUS_INCOMPLETE: String = "incomplete"
    const val RESPONSE_STATUS_FAILED: String = "failed"

    // ThinkingType.Enum
    const val THINKING_TYPE_AUTO: String = "auto"
    const val THINKING_TYPE_DISABLED: String = "disabled"
    const val THINKING_TYPE_ENABLED: String = "enabled"

    // TextType.Enum
    const val TEXT_TYPE_TEXT: String = "text"
    const val TEXT_TYPE_JSON_OBJECT: String = "json_object"
    const val TEXT_TYPE_JSON_SCHEMA: String = "json_schema"

    // ToolChoiceMode.Enum
    const val TOOL_CHOICE_MODE_AUTO: String = "auto"
    const val TOOL_CHOICE_MODE_NONE: String = "none"
    const val TOOL_CHOICE_MODE_REQUIRED: String = "required"

    // ToolType.Enum
    const val TOOL_TYPE_FUNCTION: String = "function"
    const val TOOL_TYPE_WEB_SEARCH: String = "web_search"
    const val TOOL_TYPE_IMAGE_PROCESS: String = "image_process"
    const val TOOL_TYPE_MCP: String = "mcp"
    const val TOOL_TYPE_DOUBAO_APP: String = "doubao_app"

    // UserLocationType.Enum
    const val USER_LOCATION_TYPE_APPROXIMATE: String = "approximate"

    // ObjectType.Enum
    const val OBJECT_TYPE_RESPONSE: String = "response"
    const val OBJECT_TYPE_LIST: String = "list"

    // Response lifecycle events
    const val EVENT_TYPE_RESPONSE_CREATED: String = "response.created"
    const val EVENT_TYPE_RESPONSE_IN_PROGRESS: String = "response.in_progress"
    const val EVENT_TYPE_RESPONSE_COMPLETED: String = "response.completed"
    const val EVENT_TYPE_RESPONSE_FAILED: String = "response.failed"
    const val EVENT_TYPE_RESPONSE_INCOMPLETE: String = "response.incomplete"

    // Output item events
    const val EVENT_TYPE_RESPONSE_OUTPUT_ITEM_ADDED: String = "response.output_item.added"
    const val EVENT_TYPE_RESPONSE_OUTPUT_ITEM_DONE: String = "response.output_item.done"

    // Content part events
    const val EVENT_TYPE_RESPONSE_CONTENT_PART_ADDED: String = "response.content_part.added"
    const val EVENT_TYPE_RESPONSE_CONTENT_PART_DONE: String = "response.content_part.done"

    // Output text events
    const val EVENT_TYPE_RESPONSE_OUTPUT_TEXT_DELTA: String = "response.output_text.delta"
    const val EVENT_TYPE_RESPONSE_OUTPUT_TEXT_DONE: String = "response.output_text.done"
    const val EVENT_TYPE_RESPONSE_OUTPUT_TEXT_ANNOTATION_ADDED: String = "response.output_text.annotation.added"

    // Reasoning summary events
    const val EVENT_TYPE_RESPONSE_REASONING_SUMMARY_TEXT_DELTA: String = "response.reasoning_summary_text.delta"
    const val EVENT_TYPE_RESPONSE_REASONING_SUMMARY_TEXT_DONE: String = "response.reasoning_summary_text.done"
    const val EVENT_TYPE_RESPONSE_REASONING_SUMMARY_PART_ADDED: String = "response.reasoning_summary_part.added"
    const val EVENT_TYPE_RESPONSE_REASONING_SUMMARY_PART_DONE: String = "response.reasoning_summary_part.done"

    // Function call events
    const val EVENT_TYPE_RESPONSE_FUNCTION_CALL_ARGUMENTS_DELTA: String = "response.function_call_arguments.delta"
    const val EVENT_TYPE_RESPONSE_FUNCTION_CALL_ARGUMENTS_DONE: String = "response.function_call_arguments.done"

    // Error events
    const val EVENT_TYPE_ERROR: String = "error"

    // Web search events
    const val EVENT_TYPE_RESPONSE_WEB_SEARCH_CALL_IN_PROGRESS: String = "response.web_search_call.in_progress"
    const val EVENT_TYPE_RESPONSE_WEB_SEARCH_CALL_SEARCHING: String = "response.web_search_call.searching"
    const val EVENT_TYPE_RESPONSE_WEB_SEARCH_CALL_COMPLETED: String = "response.web_search_call.completed"

    // Image process events
    const val EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_IN_PROGRESS: String = "response.image_process_call.in_progress"
    const val EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_PROGRESSING: String = "response.image_process_call.progressing"
    const val EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_COMPLETED: String = "response.image_process_call.completed"
    const val EVENT_TYPE_RESPONSE_IMAGE_PROCESS_CALL_FAILED: String = "response.image_process_call.failed"

    // MCP events
    const val EVENT_TYPE_RESPONSE_MCP_LIST_TOOLS_IN_PROGRESS: String = "response.mcp_list_tools.in_progress"
    const val EVENT_TYPE_RESPONSE_MCP_LIST_TOOLS_COMPLETED: String = "response.mcp_list_tools.completed"
    const val EVENT_TYPE_RESPONSE_MCP_CALL_IN_PROGRESS: String = "response.mcp_call.in_progress"
    const val EVENT_TYPE_RESPONSE_MCP_CALL_ARGUMENTS_DELTA: String = "response.mcp_call_arguments.delta"
    const val EVENT_TYPE_RESPONSE_MCP_CALL_ARGUMENTS_DONE: String = "response.mcp_call_arguments.done"
    const val EVENT_TYPE_RESPONSE_MCP_CALL_COMPLETED: String = "response.mcp_call.completed"
    const val EVENT_TYPE_RESPONSE_MCP_CALL_FAILED: String = "response.mcp_call.failed"

    // Doubao app events
    const val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_IN_PROGRESS: String = "response.doubao_app_call.in_progress"
    const val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_COMPLETED: String = "response.doubao_app_call.completed"
    const val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_FAILED: String = "response.doubao_app_call.failed"
    const val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_OUTPUT_TEXT_DELTA: String = "response.doubao_app_call_output_text.delta"
    const val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_OUTPUT_TEXT_DONE: String = "response.doubao_app_call_output_text.done"
    const val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_SEARCH_IN_PROGRESS: String = "response.doubao_app_call_search.in_progress"
    const val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_SEARCH_SEARCHING: String = "response.doubao_app_call_search.searching"
    const val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_SEARCH_COMPLETED: String = "response.doubao_app_call_search.completed"
    const val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_TEXT_DELTA: String = "response.doubao_app_call_reasoning_text.delta"
    const val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_TEXT_DONE: String = "response.doubao_app_call_reasoning_text.done"
    const val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_SEARCH_IN_PROGRESS: String = "response.doubao_app_call_reasoning_search.in_progress"
    const val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_SEARCH_SEARCHING: String = "response.doubao_app_call_reasoning_search.searching"
    const val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_SEARCH_COMPLETED: String = "response.doubao_app_call_reasoning_search.completed"
    const val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_BLOCK_ADDED: String = "response.doubao_app_call_block.added"
    const val EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_BLOCK_DONE: String = "response.doubao_app_call_block.done"

    // IncludeType.Enum
    const val INCLUDE_TYPE_IMAGE_URL: String = "message.input_image.image_url"

    // CacheType.Enum
    const val CACHE_TYPE_DISABLED: String = "disabled"
    const val CACHE_TYPE_ENABLED: String = "enabled"

    // MCP Enum
    const val MCP_APPROVAL_MODE_ALWAYS: String = "always"
    const val MCP_APPROVAL_MODE_NEVER: String = "never"

    // DoubaoAppBlockType.Enum
    const val DOBAO_APP_BLOCK_TYPE_OUTPUT_TEXT: String = "output_text"
    const val DOBAO_APP_BLOCK_TYPE_REASONING_TEXT: String = "reasoning_text"
    const val DOBAO_APP_BLOCK_TYPE_SEARCH: String = "search"
    const val DOBAO_APP_BLOCK_TYPE_REASONING_SEARCH: String = "reasoning_search"

    // ResponseDoubaoAppFeatureType.Enum
    const val RESPONSE_DOBAO_APP_FEATURE_TYPE_UNSPECIFIED: String = "unspecified"
    const val RESPONSE_DOBAO_APP_FEATURE_TYPE_ENABLED: String = "enabled"
    const val RESPONSE_DOBAO_APP_FEATURE_TYPE_DISABLED: String = "disabled"
}