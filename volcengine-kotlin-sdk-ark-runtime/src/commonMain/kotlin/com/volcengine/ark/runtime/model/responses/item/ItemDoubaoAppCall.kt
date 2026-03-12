package com.volcengine.ark.runtime.model.responses.item

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ItemDoubaoAppCall : BaseItem(ResponsesConstants.ITEM_TYPE_DOUBAO_APP_CALL), OutputItem {
    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("feature")
    var feature: String? = null

    @JsonProperty("blocks")
    private var blocks: List<DoubaoAppCallBlock?>? = null

    @JsonProperty("status")
    var status: String? = null

    fun getBlocks(): List<DoubaoAppCallBlock?>? {
        return blocks
    }

    fun setBlocks(blocks: List<DoubaoAppCallBlock?>?) {
        this.blocks = blocks
    }

    @Override
    fun toString(): String? {
        return "ItemDoubaoAppCall{" +
                "type='" + getType() + '\'' +
                ", id='" + id + '\'' +
                ", feature='" + feature + '\'' +
                ", blocks=" + blocks +
                ", status='" + status + '\'' +
                '}'
    }

    class Builder {
        private var id: String? = null
        private var feature: String? = null
        private var blocks: List<DoubaoAppCallBlock?>? = null
        private var status: String? = null

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun feature(feature: String?): Builder {
            this.feature = feature
            return this
        }

        fun blocks(blocks: List<DoubaoAppCallBlock?>?): Builder {
            this.blocks = blocks
            return this
        }

        fun status(status: String?): Builder {
            this.status = status
            return this
        }

        fun build(): ItemDoubaoAppCall {
            val itemDoubaoAppCall: ItemDoubaoAppCall = com.volcengine.ark.runtime.model.responses.item.ItemDoubaoAppCall()
            itemDoubaoAppCall.id = id
            itemDoubaoAppCall.feature = feature
            itemDoubaoAppCall.setBlocks(blocks)
            itemDoubaoAppCall.status = status
            return itemDoubaoAppCall
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.ItemDoubaoAppCall.Builder()
        }
    }
}
