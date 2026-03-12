package com.volcengine.ark.runtime.model.responses.item

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import com.volcengine.ark.runtime.model.responses.item.doubaoapp.DoubaoAppCallBlock
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.ITEM_TYPE_DOUBAO_APP_CALL)
data class ItemDoubaoAppCall(
    @SerialName("type")
    override val type: String = ResponsesConstants.ITEM_TYPE_DOUBAO_APP_CALL,
    @SerialName("id")
    val id: String? = null,
    @SerialName("feature")
    val feature: String? = null,
    @SerialName("blocks")
    val blocks: List<DoubaoAppCallBlock?>? = null,
    @SerialName("status")
    val status: String? = null
) : BaseItem(type) {
    constructor() : this(ResponsesConstants.ITEM_TYPE_DOUBAO_APP_CALL)
}