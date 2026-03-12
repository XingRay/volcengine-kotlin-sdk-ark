package com.volcengine.ark.runtime.model.responses.event.doubaoapp

import com.fasterxml.jackson.annotation.JsonProperty

class DoubaoAppCallReasoningSearchCompletedEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_REASONING_SEARCH_COMPLETED) {
    @JsonProperty("block_index")
    var blockIndex: Long? = null

    @JsonProperty("summary")
    var summary: String? = null

    @JsonProperty("queries")
    var queries: List<String?>? = null

    @JsonProperty("results")
    private var results: List<DoubaoAppSearchResult?>? = null

    fun getResults(): List<DoubaoAppSearchResult?>? {
        return results
    }

    fun setResults(results: List<DoubaoAppSearchResult?>?) {
        this.results = results
    }

    @Override
    fun toString(): String? {
        return "DoubaoAppCallReasoningSearchCompletedEvent{" +
                "type='" + getType() + '\'' +
                ", blockIndex=" + blockIndex +
                ", summary='" + summary + '\'' +
                ", queries=" + queries +
                ", results=" + results +
                ", itemId='" + getItemId() + '\'' +
                ", outputIndex=" + getOutputIndex() +
                ", sequenceNumber=" + getSequenceNumber() +
                '}'
    }

    class Builder {
        private val event: DoubaoAppCallReasoningSearchCompletedEvent = com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallReasoningSearchCompletedEvent()

        fun blockIndex(blockIndex: Long?): Builder {
            event.blockIndex = blockIndex
            return this
        }

        fun summary(summary: String?): Builder {
            event.summary = summary
            return this
        }

        fun queries(queries: List<String?>?): Builder {
            event.queries = queries
            return this
        }

        fun results(results: List<DoubaoAppSearchResult?>?): Builder {
            event.setResults(results)
            return this
        }

        fun build(): DoubaoAppCallReasoningSearchCompletedEvent {
            return event
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallReasoningSearchCompletedEvent.Builder()
        }
    }
}
