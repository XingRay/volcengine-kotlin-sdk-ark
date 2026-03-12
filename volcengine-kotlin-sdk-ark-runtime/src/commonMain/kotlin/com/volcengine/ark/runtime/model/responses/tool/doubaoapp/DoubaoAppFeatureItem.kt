package com.volcengine.ark.runtime.model.responses.tool.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class DoubaoAppFeatureItem(
    @SerialName("type")
    val type: String? = null,

    @SerialName("role_description")
    val roleDescription: String? = null
)
