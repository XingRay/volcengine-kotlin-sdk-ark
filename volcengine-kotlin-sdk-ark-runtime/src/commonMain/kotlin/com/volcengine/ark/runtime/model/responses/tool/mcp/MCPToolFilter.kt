package com.volcengine.ark.runtime.model.responses.tool.mcp

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
class MCPToolFilter {
    @SerialName("tool_names")
    var toolNames: List<String?>? = null


    class Builder private constructor() {
        private var toolNames: List<String?>? = null

        fun toolNames(toolNames: List<String?>?): Builder {
            this.toolNames = toolNames
            return this
        }

        fun build(): MCPToolFilter {
            val mCPToolFilter: MCPToolFilter = com.volcengine.ark.runtime.model.responses.tool.mcp.MCPToolFilter()
            mCPToolFilter.toolNames = toolNames
            return mCPToolFilter
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.mcp.MCPToolFilter.Builder()
        }
    }
}
