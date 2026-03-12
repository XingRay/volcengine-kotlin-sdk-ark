package com.volcengine.ark.runtime.model.responses.item
import kotlinx.serialization.Serializable


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(
    [JsonSubTypes.Type(value = BaseMessageItem::class, name = ResponsesConstants.ITEM_TYPE_MESSAGE), JsonSubTypes.Type(
        value = ItemFunctionToolCall::class,
        name = ResponsesConstants.ITEM_TYPE_FUNCTION_CALL
    ), JsonSubTypes.Type(value = ItemFunctionToolCallOutput::class, name = ResponsesConstants.ITEM_TYPE_FUNCTION_CALL_OUTPUT), JsonSubTypes.Type(
        value = ItemReasoning::class,
        name = ResponsesConstants.ITEM_TYPE_REASONING
    ), JsonSubTypes.Type(value = ItemReference::class, name = ResponsesConstants.ITEM_TYPE_ITEM_REFERENCE), JsonSubTypes.Type(
        value = ItemWebSearchCall::class,
        name = ResponsesConstants.ITEM_TYPE_WEB_SEARCH_CALL
    ), JsonSubTypes.Type(value = ItemImageProcess::class, name = ResponsesConstants.ITEM_TYPE_IMAGE_PROCESS), JsonSubTypes.Type(
        value = ItemMCPApprovalRequest::class,
        name = ResponsesConstants.ITEM_TYPE_MCP_APPROVAL_REQUEST
    ), JsonSubTypes.Type(value = ItemMCPApprovalResponse::class, name = ResponsesConstants.ITEM_TYPE_MCP_APPROVAL_RESPONSE), JsonSubTypes.Type(
        value = ItemMCPListTools::class,
        name = ResponsesConstants.ITEM_TYPE_MCP_LIST_TOOLS
    ), JsonSubTypes.Type(value = ItemMCPCall::class, name = ResponsesConstants.ITEM_TYPE_MCP_CALL), JsonSubTypes.Type(
        value = ItemDoubaoAppCall::class,
        name = ResponsesConstants.ITEM_TYPE_DOUBAO_APP_CALL
    )]
)
@Serializable
class BaseItem(@field:JsonProperty("type") var type: String?) 