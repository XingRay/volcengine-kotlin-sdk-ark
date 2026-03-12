package com.volcengine.ark.runtime.model.responses.item.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class DoubaoAppSearchResult(
    @SerialName("text_card")
    val textCard: DoubaoAppSearchTextItem? = null
)
