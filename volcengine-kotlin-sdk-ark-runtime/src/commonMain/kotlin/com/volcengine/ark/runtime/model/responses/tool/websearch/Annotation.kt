package com.volcengine.ark.runtime.model.responses.tool.websearch

import com.fasterxml.jackson.annotation.JsonProperty

class Annotation {
    @JsonProperty("type")
    var type: String? = null

    @JsonProperty("title")
    var title: String? = null

    @JsonProperty("url")
    var url: String? = null

    @JsonProperty("logo_url")
    var logoUrl: String? = null

    @JsonProperty("mobile_url")
    var mobileUrl: String? = null

    @JsonProperty("site_name")
    var siteName: String? = null

    @JsonProperty("publish_time")
    var publishTime: String? = null

    @JsonProperty("cover_image")
    private var coverImage: CoverImage? = null

    @JsonProperty("summary")
    var summary: String? = null

    @JsonProperty("freshness_info")
    var freshnessInfo: String? = null

    fun getCoverImage(): CoverImage? {
        return coverImage
    }

    fun setCoverImage(coverImage: CoverImage?) {
        this.coverImage = coverImage
    }

    @Override
    fun toString(): String? {
        return "Annotation{" +
                "type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", mobileUrl='" + mobileUrl + '\'' +
                ", siteName='" + siteName + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", coverImage=" + coverImage +
                ", summary='" + summary + '\'' +
                ", freshnessInfo='" + freshnessInfo + '\'' +
                '}'
    }

    class Builder {
        private var type: String? = null
        private var title: String? = null
        private var url: String? = null
        private var logoUrl: String? = null
        private var mobileUrl: String? = null
        private var siteName: String? = null
        private var publishTime: String? = null
        private var coverImage: CoverImage? = null
        private var summary: String? = null
        private var freshnessInfo: String? = null

        fun type(type: String?): Builder {
            this.type = type
            return this
        }

        fun title(title: String?): Builder {
            this.title = title
            return this
        }

        fun url(url: String?): Builder {
            this.url = url
            return this
        }

        fun logoUrl(logoUrl: String?): Builder {
            this.logoUrl = logoUrl
            return this
        }

        fun mobileUrl(mobileUrl: String?): Builder {
            this.mobileUrl = mobileUrl
            return this
        }

        fun siteName(siteName: String?): Builder {
            this.siteName = siteName
            return this
        }

        fun publishTime(publishTime: String?): Builder {
            this.publishTime = publishTime
            return this
        }

        fun coverImage(coverImage: CoverImage?): Builder {
            this.coverImage = coverImage
            return this
        }

        fun summary(summary: String?): Builder {
            this.summary = summary
            return this
        }

        fun freshnessInfo(freshnessInfo: String?): Builder {
            this.freshnessInfo = freshnessInfo
            return this
        }

        fun build(): Annotation {
            val annotation: Annotation = com.volcengine.ark.runtime.model.responses.tool.websearch.Annotation()
            annotation.type = type
            annotation.title = title
            annotation.url = url
            annotation.logoUrl = logoUrl
            annotation.mobileUrl = mobileUrl
            annotation.siteName = siteName
            annotation.publishTime = publishTime
            annotation.setCoverImage(coverImage)
            annotation.summary = summary
            annotation.freshnessInfo = freshnessInfo
            return annotation
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.websearch.Annotation.Builder()
        }
    }
}