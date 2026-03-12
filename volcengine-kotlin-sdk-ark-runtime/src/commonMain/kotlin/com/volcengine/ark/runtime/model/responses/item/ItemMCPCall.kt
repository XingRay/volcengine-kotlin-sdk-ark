package com.volcengine.ark.runtime.model.responses.item

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ItemMCPCall : BaseItem(ResponsesConstants.ITEM_TYPE_MCP_CALL), InputItem, OutputItem {
    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("server_label")
    var serverLabel: String? = null

    @JsonProperty("approval_request_id")
    var approvalRequestId: String? = null

    @JsonProperty("arguments")
    var arguments: String? = null

    @JsonProperty("error")
    var error: String? = null

    @JsonProperty("name")
    var name: String? = null

    @JsonProperty("output")
    var output: String? = null

    @Override
    fun toString(): String? {
        return "ItemMCPCall{" +
                "id='" + id + '\'' +
                ", serverLabel='" + serverLabel + '\'' +
                ", approvalRequestId='" + approvalRequestId + '\'' +
                ", arguments='" + arguments + '\'' +
                ", error='" + error + '\'' +
                ", name='" + name + '\'' +
                ", output='" + output + '\'' +
                '}'
    }

    class Builder private constructor() {
        private var output: String? = null
        private var name: String? = null
        private var error: String? = null
        private var arguments: String? = null
        private var approvalRequestId: String? = null
        private var serverLabel: String? = null
        private var id: String? = null

        fun output(output: String?): Builder {
            this.output = output
            return this
        }

        fun name(name: String?): Builder {
            this.name = name
            return this
        }

        fun error(error: String?): Builder {
            this.error = error
            return this
        }

        fun arguments(arguments: String?): Builder {
            this.arguments = arguments
            return this
        }

        fun approvalRequestId(approvalRequestId: String?): Builder {
            this.approvalRequestId = approvalRequestId
            return this
        }

        fun serverLabel(serverLabel: String?): Builder {
            this.serverLabel = serverLabel
            return this
        }

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun build(): ItemMCPCall {
            val itemMCPCall: ItemMCPCall = com.volcengine.ark.runtime.model.responses.item.ItemMCPCall()
            itemMCPCall.output = output
            itemMCPCall.name = name
            itemMCPCall.error = error
            itemMCPCall.arguments = arguments
            itemMCPCall.approvalRequestId = approvalRequestId
            itemMCPCall.serverLabel = serverLabel
            itemMCPCall.id = id
            return itemMCPCall
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.ItemMCPCall.Builder()
        }
    }
}
