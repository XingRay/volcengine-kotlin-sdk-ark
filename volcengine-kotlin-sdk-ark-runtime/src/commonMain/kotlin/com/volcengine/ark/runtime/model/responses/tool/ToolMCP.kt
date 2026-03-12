package com.volcengine.ark.runtime.model.responses.tool

import com.fasterxml.jackson.annotation.JsonProperty

class ToolMCP : ResponsesTool(ResponsesConstants.TOOL_TYPE_MCP) {
    @JsonProperty("server_label")
    var serverLabel: String? = null

    @JsonProperty("server_url")
    var serverUrl: String? = null

    @JsonProperty("allowed_tools")
    private var allowedTools: MCPAllowedTools? = null

    @JsonProperty("headers")
    var headers: Map<String?, String?>? = null

    @JsonProperty("require_approval")
    private var requireApproval: MCPRequireApproval? = null

    @JsonProperty("server_description")
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

    @Override
    fun toString(): String? {
        return "ToolMCP{" +
                "serverLabel='" + serverLabel + '\'' +
                ", serverUrl='" + serverUrl + '\'' +
                ", allowedTools=" + allowedTools +
                ", headers=" + headers +
                ", requireApproval=" + requireApproval +
                ", serverDescription='" + serverDescription + '\'' +
                '}'
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
