package com.volcengine.ark.runtime.model.responses.tool
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ToolMCP : ResponsesTool(ResponsesConstants.TOOL_TYPE_MCP) {
    @SerialName("server_label")
    var serverLabel: String? = null

    @SerialName("server_url")
    var serverUrl: String? = null

    @SerialName("allowed_tools")
    private var allowedTools: MCPAllowedTools? = null

    @SerialName("headers")
    var headers: Map<String?, String?>? = null

    @SerialName("require_approval")
    private var requireApproval: MCPRequireApproval? = null

    @SerialName("server_description")
    var serverDescription: String? = null

    fun getAllowedTools(): MCPAllowedTools? {
        return allowedTools
    }

    fun setAllowedTools(allowedTools: MCPAllowedTools?) {
        this.allowedTools = allowedTools
    }

    fun getRequireApproval(): MCPRequireApproval? {
        return requireApproval
    }

    fun setRequireApproval(requireApproval: MCPRequireApproval?) {
        this.requireApproval = requireApproval
    }


    class Builder private constructor() {
        private var serverDescription: String? = null
        private var requireApproval: MCPRequireApproval? = null
        private var headers: Map<String?, String?>? = null
        private var allowedTools: MCPAllowedTools? = null
        private var serverUrl: String? = null
        private var serverLabel: String? = null

        fun serverDescription(serverDescription: String?): Builder {
            this.serverDescription = serverDescription
            return this
        }

        fun requireApproval(requireApproval: MCPRequireApproval?): Builder {
            this.requireApproval = requireApproval
            return this
        }

        fun headers(headers: Map<String?, String?>?): Builder {
            this.headers = headers
            return this
        }

        fun allowedTools(allowedTools: MCPAllowedTools?): Builder {
            this.allowedTools = allowedTools
            return this
        }

        fun serverUrl(serverUrl: String?): Builder {
            this.serverUrl = serverUrl
            return this
        }

        fun serverLabel(serverLabel: String?): Builder {
            this.serverLabel = serverLabel
            return this
        }

        fun build(): ToolMCP {
            val toolMCP: ToolMCP = com.volcengine.ark.runtime.model.responses.tool.ToolMCP()
            toolMCP.serverDescription = serverDescription
            toolMCP.setRequireApproval(requireApproval)
            toolMCP.headers = headers
            toolMCP.setAllowedTools(allowedTools)
            toolMCP.serverUrl = serverUrl
            toolMCP.serverLabel = serverLabel
            return toolMCP
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.ToolMCP.Builder()
        }
    }
}
