package com.volcengine.ark.runtime.model.responses.item
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ItemDoubaoAppCall : BaseItem(ResponsesConstants.ITEM_TYPE_DOUBAO_APP_CALL), OutputItem {
    @SerialName("id")
    var id: String? = null

    @SerialName("feature")
    var feature: String? = null

    @SerialName("blocks")
    private var blocks: List<DoubaoAppCallBlock?>? = null

    @SerialName("status")
    var status: String? = null

    fun getBlocks(): List<DoubaoAppCallBlock?>? {
        return blocks
    }

    fun setBlocks(blocks: List<DoubaoAppCallBlock?>?) {
        this.blocks = blocks
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
