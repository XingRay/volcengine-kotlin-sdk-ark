package com.volcengine.ark.runtime.model.responses.tool.websearch

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Annotation(
    @SerialName("type")
    val type: String? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("url")
    val url: String? = null,

    @SerialName("logo_url")
    val logoUrl: String? = null,

    @SerialName("mobile_url")
    val mobileUrl: String? = null,

    @SerialName("site_name")
    val siteName: String? = null,

    @SerialName("publish_time")
    val publishTime: String? = null,

    @SerialName("cover_image")
    val coverImage: CoverImage? = null,

    @SerialName("summary")
    val summary: String? = null,

    @SerialName("freshness_info")
    val freshnessInfo: String? = null
)