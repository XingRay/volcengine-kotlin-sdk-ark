package com.volcengine.ark.runtime.model.responses.tool.mcp

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(using = com.volcengine.ark.runtime.model.responses.tool.mcp.MCPAllowedTools.MCPAllowedToolsSerializer::class)
@JsonDeserialize(using = com.volcengine.ark.runtime.model.responses.tool.mcp.MCPAllowedTools.MCPAllowedToolsDeserializer::class)
class MCPAllowedTools {
    var allowedTools: List<String?>? = null

    private var filter: MCPToolFilter? = null

    fun getFilter(): MCPToolFilter? {
        return filter
    }

    fun setFilter(filter: MCPToolFilter?) {
        this.filter = filter
    }

    @Override
    fun toString(): String? {
        return "MCPAllowedTools{" +
                "allowedTools=" + allowedTools +
                ", toolFilter=" + filter +
                '}'
    }

    internal class MCPAllowedToolsSerializer : JsonSerializer<MCPAllowedTools?>() {
        @Override
        @Throws(IOException::class)
        fun serialize(value: MCPAllowedTools, gen: JsonGenerator, serializers: SerializerProvider?) {
            if (value.allowedTools != null) {
                gen.writeArray(value.allowedTools.toArray(arrayOfNulls<String>(0)), 0, value.allowedTools.size())
            } else if (value.filter != null) {
                gen.writeObject(value.filter)
            } else {
                gen.writeNull()
            }
        }
    }

    internal class MCPAllowedToolsDeserializer : JsonDeserializer<MCPAllowedTools?>() {
        @Override
        @Throws(IOException::class, JsonProcessingException::class)
        fun deserialize(p: JsonParser, ctxt: DeserializationContext?): MCPAllowedTools {
            val node: JsonNode = p.getCodec().readTree(p)
            if (node.isArray()) {
                val allowedTools: List<String?>? = Arrays.asList(p.getCodec().treeToValue(node, Array<String>::class.java))
                return com.volcengine.ark.runtime.model.responses.tool.mcp.MCPAllowedTools.Companion.builder().allowedTools(allowedTools).build()
            } else if (node.isObject()) {
                val toolFilter: MCPToolFilter? = p.getCodec().treeToValue(node, MCPToolFilter::class.java)
                return com.volcengine.ark.runtime.model.responses.tool.mcp.MCPAllowedTools.Companion.builder().toolFilter(toolFilter).build()
            } else {
                throw IOException("Unexpected JSON type for MCPAllowedTools")
            }
        }
    }

    class Builder private constructor() {
        private var allowedTools: List<String?>? = null
        private var toolFilter: MCPToolFilter? = null

        fun allowedTools(allowedTools: List<String?>?): Builder {
            this.allowedTools = allowedTools
            return this
        }

        fun toolFilter(toolFilter: MCPToolFilter?): Builder {
            this.toolFilter = toolFilter
            return this
        }

        fun build(): MCPAllowedTools {
            val mCPAllowedTools: MCPAllowedTools = com.volcengine.ark.runtime.model.responses.tool.mcp.MCPAllowedTools()
            mCPAllowedTools.allowedTools = allowedTools
            mCPAllowedTools.setFilter(toolFilter)
            return mCPAllowedTools
        }

        companion object {
            fun aMCPAllowedTools(): Builder {
                return com.volcengine.ark.runtime.model.responses.tool.mcp.MCPAllowedTools.Builder()
            }
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.mcp.MCPAllowedTools.Builder()
        }
    }
}
