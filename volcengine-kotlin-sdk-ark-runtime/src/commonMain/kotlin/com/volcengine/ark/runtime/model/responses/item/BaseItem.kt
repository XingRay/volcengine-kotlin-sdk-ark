package com.volcengine.ark.runtime.model.responses.item

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable(with = BaseItemPolymorphicSerializer::class)
abstract class BaseItem(open val type: String) {
    constructor() : this("")
}

object BaseItemPolymorphicSerializer : JsonContentPolymorphicSerializer<BaseItem>(BaseItem::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out BaseItem> {
        val type = element.jsonObject["type"]?.jsonPrimitive?.content
            ?: throw SerializationException("缺失 type 字段")
        return when (type) {
            ResponsesConstants.ITEM_TYPE_MESSAGE -> {
                // 根据是否有 status 字段判断是 ItemOutputMessage 还是 ItemEasyMessage
                val hasStatus = element.jsonObject.containsKey("status")
                if (hasStatus) {
                    ItemOutputMessage.serializer()
                } else {
                    ItemEasyMessage.serializer()
                }
            }
            ResponsesConstants.ITEM_TYPE_FUNCTION_CALL -> ItemFunctionToolCall.serializer()
            ResponsesConstants.ITEM_TYPE_FUNCTION_CALL_OUTPUT -> ItemFunctionToolCallOutput.serializer()
            ResponsesConstants.ITEM_TYPE_REASONING -> ItemReasoning.serializer()
            ResponsesConstants.ITEM_TYPE_ITEM_REFERENCE -> ItemReference.serializer()
            ResponsesConstants.ITEM_TYPE_WEB_SEARCH_CALL -> ItemWebSearchCall.serializer()
            ResponsesConstants.ITEM_TYPE_IMAGE_PROCESS -> ItemImageProcess.serializer()
            ResponsesConstants.ITEM_TYPE_MCP_APPROVAL_REQUEST -> ItemMCPApprovalRequest.serializer()
            ResponsesConstants.ITEM_TYPE_MCP_APPROVAL_RESPONSE -> ItemMCPApprovalResponse.serializer()
            ResponsesConstants.ITEM_TYPE_MCP_LIST_TOOLS -> ItemMCPListTools.serializer()
            ResponsesConstants.ITEM_TYPE_MCP_CALL -> ItemMCPCall.serializer()
            ResponsesConstants.ITEM_TYPE_KNOWLEDGE_SEARCH_CALL -> ItemKnowledgeSearchCall.serializer()
            ResponsesConstants.ITEM_TYPE_DOUBAO_APP_CALL -> ItemDoubaoAppCall.serializer()
            else -> throw SerializationException("未知的 item type: $type")
        }
    }
}
