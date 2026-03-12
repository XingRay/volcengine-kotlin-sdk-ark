package com.volcengine.ark.runtime.model.responses.item.doubaoapp

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DoubaoAppCallBlockOutputText(
    @SerialName("id")
    val id: String? = null,

    @SerialName("type")
    val type: String? = ResponsesConstants.DOBAO_APP_BLOCK_TYPE_OUTPUT_TEXT,

    @SerialName("text")
    val text: String? = null,

    @SerialName("status")
    val status: String? = null,

    @SerialName("parent_id")
    val parentId: String? = null
) : DoubaoAppCallBlock()
