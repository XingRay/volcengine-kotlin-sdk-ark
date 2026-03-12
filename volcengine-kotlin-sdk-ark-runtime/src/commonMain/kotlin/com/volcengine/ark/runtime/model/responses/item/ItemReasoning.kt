package com.volcengine.ark.runtime.model.responses.item

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ItemReasoning : BaseItem(ResponsesConstants.ITEM_TYPE_REASONING), OutputItem {
    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("summary")
    private var summary: List<ReasoningSummaryPart?>? = null

    @JsonProperty("status")
    var status: String? = null

    fun getSummary(): List<ReasoningSummaryPart?>? {
        return summary
    }

    fun setSummary(summary: List<ReasoningSummaryPart?>?) {
        this.summary = summary
    }

    @Override
    fun toString(): String? {
        return "ItemReasoning{" +
                "id='" + id + '\'' +
                ", type='" + getType() + '\'' +
                ", summary=" + summary +
                ", status='" + status + '\'' +
                '}'
    }

    class Builder {
        private var id: String? = null
        private var summary: List<ReasoningSummaryPart?>? = null
        private var status: String? = null

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun summary(summary: List<ReasoningSummaryPart?>?): Builder {
            this.summary = summary
            return this
        }

        fun status(status: String?): Builder {
            this.status = status
            return this
        }

        fun build(): ItemReasoning {
            val itemReasoning: ItemReasoning = com.volcengine.ark.runtime.model.responses.item.ItemReasoning()
            itemReasoning.id = id
            itemReasoning.setSummary(summary)
            itemReasoning.status = status
            return itemReasoning
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.ItemReasoning.Builder()
        }
    }
}