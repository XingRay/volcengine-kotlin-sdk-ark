package com.volcengine.ark.runtime.model.responses.item

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessAction
import com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessArguments
import com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessError
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ResponsesConstants.ITEM_TYPE_IMAGE_PROCESS)
data class ItemImageProcess(
    @SerialName("type")
    override val type: String = ResponsesConstants.ITEM_TYPE_IMAGE_PROCESS,
    @SerialName("action")
    val action: ImageProcessAction? = null,
    @SerialName("arguments")
    val arguments: ImageProcessArguments? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("error")
    val error: ImageProcessError? = null
) : BaseItem(type) {
    constructor() : this(ResponsesConstants.ITEM_TYPE_IMAGE_PROCESS)
}