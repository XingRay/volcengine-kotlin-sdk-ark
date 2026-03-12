package com.volcengine.ark.runtime.model.bot.completion.chat.reference

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject


@Serializable
data class BotChatResultReference(
    /**
     * Search: Url
     */
    val url: String? = null,

    /**
     * Search: Logo Url
     */
    @SerialName("logo_url")
    val logoUrl: String? = null,

    /**
     * Search: Mobile Url
     */
    @SerialName("mobile_url")
    val mobileUrl: String? = null,

    /**
     * Search: Site Name
     */
    @SerialName("site_name")
    val siteName: String? = null,

    /**
     * Search: Title
     */
    val title: String? = null,

    /**
     * Search: CoverImage
     */
    @SerialName("cover_image")
    val coverImage: BotCoverImage? = null,

    /**
     * Search: Summary
     */
    val summary: String? = null,

    /**
     * Search: Publish Time
     */
    @SerialName("publish_time")
    val publishTime: String? = null,

    /**
     * KnowledgeBase: Collection Name
     */
    @SerialName("collection_name")
    val collectionName: String? = null,

    /**
     * KnowledgeBase: Project Name
     */
    val project: String? = null,

    /**
     * KnowledgeBase: Doc Id
     */
    @SerialName("doc_id")
    val docId: String? = null,

    /**
     * KnowledgeBase: Doc Name
     */
    @SerialName("doc_name")
    val docName: String? = null,

    /**
     * KnowledgeBase: Doc Type
     */
    @SerialName("doc_type")
    val docType: String? = null,

    /**
     * KnowledgeBase: Doc Title
     */
    @SerialName("doc_title")
    val docTitle: String? = null,

    /**
     * KnowledgeBase: Chunk Id
     */
    @SerialName("chunk_id")
    val chunkId: String? = null,

    /**
     * KnowledgeBase: Chunk Title
     */
    @SerialName("chunk_title")
    val chunkTitle: String? = null,

    /**
     * Page Nums
     *
     *
     *
     *
     */
    @SerialName("page_nums")
    val pageNums: String? = null,

    /**
     * Origin Text Token Len
     *
     *
     *
     *
     */
    @SerialName("origin_text_token_len")
    val originTextTokenLen: Int? = null,

    /**
     * File Name
     *
     *
     *
     *
     */
    @SerialName("file_name")
    val fileName: String? = null,

    /**
     * Extra
     */
    @SerialName("extra")
    val extra: JsonObject? = null,
)
