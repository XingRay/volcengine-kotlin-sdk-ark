package com.volcengine.ark.runtime.model.responses.tool.websearch

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Annotation(
    @SerialName("type")
    var type: String? = null,

    @SerialName("title")
    var title: String? = null,

    @SerialName("url")
    var url: String? = null,

    @SerialName("logo_url")
    var logoUrl: String? = null,

    @SerialName("mobile_url")
    var mobileUrl: String? = null,

    @SerialName("site_name")
    var siteName: String? = null,

    @SerialName("publish_time")
    var publishTime: String? = null,

    @SerialName("cover_image")
    private var coverImage: CoverImage? = null,

    @SerialName("summary")
    var summary: String? = null,

    @SerialName("freshness_info")
    var freshnessInfo: String? = null,
)