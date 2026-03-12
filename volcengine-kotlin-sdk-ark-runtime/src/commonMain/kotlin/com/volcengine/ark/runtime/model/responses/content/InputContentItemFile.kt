package com.volcengine.ark.runtime.model.responses.content

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ContentItemType.CONTENT_ITEM_TYPE_INPUT_FILE)
data class InputContentItemFile(
    @SerialName("type")
    override val type: String = ContentItemType.CONTENT_ITEM_TYPE_INPUT_FILE,
    @SerialName("file_url")
    val fileUrl: String? = null,
    @SerialName("file_id")
    val fileId: String? = null,
    @SerialName("file_data")
    val fileData: String? = null,
    @SerialName("filename")
    val fileName: String? = null
) : InputContentItem(type)