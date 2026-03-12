package com.volcengine.ark.runtime.model.responses.tool.mcp

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class MCPToolApprovalFilter {
    @JsonProperty("always")
    private var always: MCPToolFilter? = null

    @JsonProperty("never")
    private var never: MCPToolFilter? = null

    fun getAlways(): MCPToolFilter? {
        return always
    }

    fun setAlways(always: MCPToolFilter?) {
        this.always = always
    }

    fun getNever(): MCPToolFilter? {
        return never
    }

    fun setNever(never: MCPToolFilter?) {
        this.never = never
    }

    @Override
    fun toString(): String? {
        return "MCPToolApprovalFilter{" +
                "always=" + always +
                ", never=" + never +
                '}'
    }

    class Builder private constructor() {
        private var always: MCPToolFilter? = null
        private var never: MCPToolFilter? = null

        fun always(always: MCPToolFilter?): Builder {
            this.always = always
            return this
        }

        fun never(never: MCPToolFilter?): Builder {
            this.never = never
            return this
        }

        fun build(): MCPToolApprovalFilter {
            val mCPToolApprovalFilter: MCPToolApprovalFilter = com.volcengine.ark.runtime.model.responses.tool.mcp.MCPToolApprovalFilter()
            mCPToolApprovalFilter.setAlways(always)
            mCPToolApprovalFilter.setNever(never)
            return mCPToolApprovalFilter
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.mcp.MCPToolApprovalFilter.Builder()
        }
    }
}
