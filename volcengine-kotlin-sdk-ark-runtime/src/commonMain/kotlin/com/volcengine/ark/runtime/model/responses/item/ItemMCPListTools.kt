package com.volcengine.ark.runtime.model.responses.item
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ItemMCPListTools : BaseItem(ResponsesConstants.ITEM_TYPE_MCP_LIST_TOOLS), InputItem, OutputItem {
    @SerialName("id")
    var id: String? = null

    @SerialName("server_label")
    var serverLabel: String? = null

    @SerialName("tools")
    private var tools: List<MCPTool?>? = null

    fun getTools(): List<MCPTool?>? {
        return tools
    }

    fun setTools(tools: List<MCPTool?>?) {
        this.tools = tools
    }


    class Builder private constructor() {
        private var id: String? = null
        private var serverLabel: String? = null
        private var tools: List<MCPTool?>? = null

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun serverLabel(serverLabel: String?): Builder {
            this.serverLabel = serverLabel
            return this
        }

        fun tools(tools: List<MCPTool?>?): Builder {
            this.tools = tools
            return this
        }

        fun build(): ItemMCPListTools {
            val itemMCPListTools: ItemMCPListTools = com.volcengine.ark.runtime.model.responses.item.ItemMCPListTools()
            itemMCPListTools.id = id
            itemMCPListTools.serverLabel = serverLabel
            itemMCPListTools.setTools(tools)
            return itemMCPListTools
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.item.ItemMCPListTools.Builder()
        }
    }
}
