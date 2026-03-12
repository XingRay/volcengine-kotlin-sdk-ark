package com.volcengine.ark.runtime.model.responses.tool.mcp

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties
class MCPToolFilter {
    @JsonProperty("tool_names")
    var toolNames: List<String?>? = null

    @Override
    fun toString(): String? {
        return "MCPToolFilter{" +
                "toolNames=" + toolNames +
                '}'
    }

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
