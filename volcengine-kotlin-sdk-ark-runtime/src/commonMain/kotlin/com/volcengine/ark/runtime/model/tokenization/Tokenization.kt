package com.volcengine.ark.runtime.model.tokenization

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Tokenization {
    var index: Integer? = null

    var `object`: String? = null
        set(object) {
            field = this.`object`
        }

    @JsonProperty("total_tokens")
    var totalTokens: Integer? = null

    @JsonProperty("token_ids")
    var tokenIds: List<Integer?>? = null

    @JsonProperty("offset_mapping")
    var offsetMapping: List<List<Integer?>?>? = null

    fun getIndex(): Integer? {
        return index
    }

    fun setIndex(index: Integer?) {
        this.index = index
    }

    fun getTotalTokens(): Integer? {
        return totalTokens
    }

    fun setTotalTokens(totalTokens: Integer?) {
        this.totalTokens = totalTokens
    }

    fun getTokenIds(): List<Integer?>? {
        return tokenIds
    }

    fun setTokenIds(tokenIds: List<Integer?>?) {
        this.tokenIds = tokenIds
    }

    fun getOffsetMapping(): List<List<Integer?>?>? {
        return offsetMapping
    }

    fun setOffsetMapping(offsetMapping: List<List<Integer?>?>?) {
        this.offsetMapping = offsetMapping
    }

    @Override
    fun toString(): String? {
        return "Tokenization{" +
                "index=" + index +
                ", object='" + this.`object` + '\'' +
                ", totalTokens=" + totalTokens +
                ", tokenIds=" + tokenIds +
                ", offsetMapping=" + offsetMapping +
                '}'
    }
}
