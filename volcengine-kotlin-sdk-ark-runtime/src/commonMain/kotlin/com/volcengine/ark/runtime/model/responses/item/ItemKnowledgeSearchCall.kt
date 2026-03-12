package com.volcengine.ark.runtime.model.responses.item

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.ITEM_TYPE_KNOWLEDGE_SEARCH_CALL)
data class ItemKnowledgeSearchCall(
    @SerialName("type")
    override val type: String = ResponsesConstants.ITEM_TYPE_KNOWLEDGE_SEARCH_CALL,
    @SerialName("id")
    val id: String? = null,
    @SerialName("status")
    val status: String? = null
) : BaseItem(type) {
    constructor() : this(ResponsesConstants.ITEM_TYPE_KNOWLEDGE_SEARCH_CALL)
}
