package com.volcengine.ark.runtime.model.responses.item

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = com.volcengine.ark.runtime.model.responses.item.BaseMessageItem.BaseMessageItemDeserializer::class)
class BaseMessageItem : BaseItem(ResponsesConstants.ITEM_TYPE_MESSAGE) {
    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("role")
    var role: String? = null

    internal class BaseMessageItemDeserializer : JsonDeserializer<BaseMessageItem?>() {
        @Override
        @Throws(IOException::class)
        fun deserialize(p: JsonParser, ctxt: DeserializationContext?): BaseMessageItem {
            val node: JsonNode = p.getCodec().readTree(p)
            val status: JsonNode? = node.get("status")
            val targetType: Class<out BaseMessageItem?>?
            if (status != null) {
                targetType = ItemOutputMessage::class.java
            } else {
                targetType = ItemEasyMessage::class.java
            }

            return p.getCodec().treeToValue(node, targetType)
        }
    }
}
