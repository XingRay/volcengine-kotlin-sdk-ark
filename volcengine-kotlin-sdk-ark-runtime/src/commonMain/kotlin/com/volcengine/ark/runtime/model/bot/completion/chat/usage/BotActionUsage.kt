package com.volcengine.ark.runtime.model.bot.completion.chat.usage

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class BotActionUsage {
    /**
     * For Search Action: The source type name
     */
    var name: String? = null

    @JsonProperty("prompt_tokens")
    var promptTokens: String? = null

    @JsonProperty("completion_tokens")
    private var completionTokens: Integer? = null

    @JsonProperty("total_tokens")
    private var totalTokens: Integer? = null

    /**
     * For Search Action: return the search count from this source type
     */
    @JsonProperty("search_count")
    private var searchCount: Integer? = null

    @JsonProperty("action_name")
    var actionName: String? = null

    @JsonProperty("count")
    private var count: Integer? = null

    fun getCompletionTokens(): Integer? {
        return completionTokens
    }

    fun setCompletionTokens(completionTokens: Integer?) {
        this.completionTokens = completionTokens
    }

    fun getTotalTokens(): Integer? {
        return totalTokens
    }

    fun setTotalTokens(totalTokens: Integer?) {
        this.totalTokens = totalTokens
    }

    fun getSearchCount(): Integer? {
        return searchCount
    }

    fun setSearchCount(searchCount: Integer?) {
        this.searchCount = searchCount
    }

    fun getCount(): Integer? {
        return count
    }

    fun setCount(count: Integer?) {
        this.count = count
    }

    @Override
    fun toString(): String? {
        return "BotActionUsage{" +
                "name='" + name + '\'' +
                ", promptTokens='" + promptTokens + '\'' +
                ", completionTokens=" + completionTokens +
                ", totalTokens=" + totalTokens +
                ", searchCount=" + searchCount +
                ", actionName='" + actionName + '\'' +
                ", count=" + count +
                '}'
    }
}
