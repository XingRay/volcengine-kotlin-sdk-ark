package com.volcengine.ark.runtime.model.responses.event.doubaoapp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class DoubaoAppCallSearchCompletedEvent : ItemEvent(ResponsesConstants.EVENT_TYPE_RESPONSE_DOUBAO_APP_CALL_SEARCH_COMPLETED) {
    @SerialName("block_index")
    var blockIndex: Long? = null

    @SerialName("summary")
    var summary: String? = null

    @SerialName("queries")
    var queries: List<String?>? = null

    @SerialName("results")
    private var results: List<DoubaoAppSearchResult?>? = null

    fun getResults(): List<DoubaoAppSearchResult?>? {
        return results
    }

    fun setResults(results: List<DoubaoAppSearchResult?>?) {
        this.results = results
    }


    class Builder {
        private val event: DoubaoAppCallSearchCompletedEvent = com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallSearchCompletedEvent()

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

        fun build(): DoubaoAppCallSearchCompletedEvent {
            return event
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.event.doubaoapp.DoubaoAppCallSearchCompletedEvent.Builder()
        }
    }
}
