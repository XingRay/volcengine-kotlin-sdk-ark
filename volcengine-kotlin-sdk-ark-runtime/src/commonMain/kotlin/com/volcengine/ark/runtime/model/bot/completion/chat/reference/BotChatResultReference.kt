package com.volcengine.ark.runtime.model.bot.completion.chat.reference

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class BotChatResultReference {
    /**
     * Search: Url
     */
    var url: String? = null

    /**
     * Search: Logo Url
     */
    @JsonProperty("logo_url")
    var logoUrl: String? = null

    /**
     * Search: Mobile Url
     */
    @JsonProperty("mobile_url")
    var mobileUrl: String? = null

    /**
     * Search: Site Name
     */
    @JsonProperty("site_name")
    var siteName: String? = null

    /**
     * Search: Title
     */
    var title: String? = null

    /**
     * Search: CoverImage
     */
    @JsonProperty("cover_image")
    private var coverImage: BotCoverImage? = null

    /**
     * Search: Summary
     */
    var summary: String? = null

    /**
     * Search: Publish Time
     */
    @JsonProperty("publish_time")
    var publishTime: String? = null

    /**
     * KnowledgeBase: Collection Name
     */
    @JsonProperty("collection_name")
    var collectionName: String? = null

    /**
     * KnowledgeBase: Project Name
     */
    var project: String? = null

    /**
     * KnowledgeBase: Doc Id
     */
    @JsonProperty("doc_id")
    var docId: String? = null

    /**
     * KnowledgeBase: Doc Name
     */
    @JsonProperty("doc_name")
    var docName: String? = null

    /**
     * KnowledgeBase: Doc Type
     */
    @JsonProperty("doc_type")
    var docType: String? = null

    /**
     * KnowledgeBase: Doc Title
     */
    @JsonProperty("doc_title")
    var docTitle: String? = null

    /**
     * KnowledgeBase: Chunk Id
     */
    @JsonProperty("chunk_id")
    var chunkId: String? = null

    /**
     * KnowledgeBase: Chunk Title
     */
    @JsonProperty("chunk_title")
    var chunkTitle: String? = null

    /**
     * Page Nums
     * 
     * 
     * 
     * 
     */
    @JsonProperty("page_nums")
    var pageNums: String? = null

    /**
     * Origin Text Token Len
     * 
     * 
     * 
     * 
     */
    @JsonProperty("origin_text_token_len")
    private var originTextTokenLen: Integer? = null

    /**
     * File Name
     * 
     * 
     * 
     * 
     */
    @JsonProperty("file_name")
    var fileName: String? = null

    /**
     * Extra
     */
    @JsonProperty("extra")
    private var extra: Map<String?, Object?>? = null

    fun getCoverImage(): BotCoverImage? {
        return coverImage
    }

    fun setCoverImage(coverImage: BotCoverImage?) {
        this.coverImage = coverImage
    }

    fun getOriginTextTokenLen(): Integer? {
        return originTextTokenLen
    }

    fun setOriginTextTokenLen(originTextTokenLen: Integer?) {
        this.originTextTokenLen = originTextTokenLen
    }

    fun getExtra(): Map<String?, Object?>? {
        return extra
    }

    fun setExtra(extra: Map<String?, Object?>?) {
        this.extra = extra
    }
}
