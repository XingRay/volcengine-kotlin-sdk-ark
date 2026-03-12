package com.volcengine.ark.runtime.model.responses.tool.mcp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class MCPTool {
    @SerialName("name")
    var name: String? = null

    @SerialName("description")
    var description: String? = null

    @SerialName("input_schema")
    private var inputSchema: Map<String?, Object?>? = null

    fun getInputSchema(): Map<String?, Object?>? {
        return inputSchema
    }

    fun setInputSchema(inputSchema: Map<String?, Object?>?) {
        this.inputSchema = inputSchema
    }


    class Builder private constructor() {
        private var name: String? = null
        private var description: String? = null
        private var inputSchema: Map<String?, Object?>? = null

        fun name(name: String?): Builder {
            this.name = name
            return this
        }

        fun description(description: String?): Builder {
            this.description = description
            return this
        }

        fun inputSchema(inputSchema: Map<String?, Object?>?): Builder {
            this.inputSchema = inputSchema
            return this
        }

        fun build(): MCPTool {
            val mCPTool: MCPTool = com.volcengine.ark.runtime.model.responses.tool.mcp.MCPTool()
            mCPTool.name = name
            mCPTool.description = description
            mCPTool.setInputSchema(inputSchema)
            return mCPTool
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.mcp.MCPTool.Builder()
        }
    }
}
