package com.volcengine.ark.runtime.model.responses.item.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class DoubaoAppSearchTextItem(
    @SerialName("title")
    val title: String? = null,

    @SerialName("sitename")
    val siteName: String? = null,

    @SerialName("url")
    val url: String? = null
)
