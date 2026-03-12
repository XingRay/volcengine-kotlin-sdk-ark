package com.volcengine.ark.runtime.model.responses.tool.mcp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class MCPToolApprovalFilter {
    @SerialName("always")
    private var always: MCPToolFilter? = null

    @SerialName("never")
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
