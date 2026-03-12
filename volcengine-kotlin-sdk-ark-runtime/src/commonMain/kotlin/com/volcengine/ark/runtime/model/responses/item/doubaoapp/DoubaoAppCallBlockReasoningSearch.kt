package com.volcengine.ark.runtime.model.responses.item.doubaoapp

import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DoubaoAppCallBlockReasoningSearch(
    @SerialName("id")
    val id: String? = null,

    @SerialName("type")
    val type: String? = ResponsesConstants.DOBAO_APP_BLOCK_TYPE_REASONING_SEARCH,

    @SerialName("summary")
    val summary: String? = null,

    @SerialName("queries")
    val queries: List<String?>? = null,

    @SerialName("results")
    val results: List<DoubaoAppSearchResult?>? = null,

    @SerialName("status")
    val status: String? = null,

    @SerialName("parent_id")
    val parentId: String? = null
) : DoubaoAppCallBlock()
