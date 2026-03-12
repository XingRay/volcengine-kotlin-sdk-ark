package com.volcengine.ark.runtime.model.responses.tool.mcp
import kotlinx.serialization.Serializable


@JsonSerialize(using = com.volcengine.ark.runtime.model.responses.tool.mcp.MCPRequireApproval.MCPRequireApprovalSerializer::class)
@JsonDeserialize(using = com.volcengine.ark.runtime.model.responses.tool.mcp.MCPRequireApproval.MCPRequireApprovalDeserializer::class)
@Serializable
class MCPRequireApproval {
    var mode: String? = null

    private var filter: MCPToolApprovalFilter? = null

    fun getFilter(): MCPToolApprovalFilter? {
        return filter
    }

    fun setFilter(filter: MCPToolApprovalFilter?) {
        this.filter = filter
    }


    internal class MCPRequireApprovalSerializer : JsonSerializer<MCPRequireApproval?>() {
        @Override
        @Throws(IOException::class)
        fun serialize(value: MCPRequireApproval, gen: JsonGenerator, serializers: SerializerProvider?) {
            if (value.mode != null) {
                gen.writeString(value.mode)
            } else if (value.filter != null) {
                gen.writeObject(value.filter)
            } else {
                gen.writeNull()
            }
        }
    }

    internal class MCPRequireApprovalDeserializer : JsonDeserializer<MCPRequireApproval?>() {
        @Override
        @Throws(IOException::class, JsonProcessingException::class)
        fun deserialize(p: JsonParser, ctxt: DeserializationContext?): MCPRequireApproval {
            val node: JsonNode = p.getCodec().readTree(p)
            if (node.isObject()) {
                val toolFilter: MCPToolApprovalFilter? = p.getCodec().treeToValue(node, MCPToolApprovalFilter::class.java)
                return com.volcengine.ark.runtime.model.responses.tool.mcp.MCPRequireApproval.Companion.builder().filter(toolFilter).build()
            } else if (node.isTextual()) {
                return com.volcengine.ark.runtime.model.responses.tool.mcp.MCPRequireApproval.Companion.builder().mode(node.asText()).build()
            } else {
                throw IOException("Unexpected JSON type for MCPRequireApproval")
            }
        }
    }

    class Builder private constructor() {
        private var mode: String? = null
        private var filter: MCPToolApprovalFilter? = null

        fun mode(mode: String?): Builder {
            this.mode = mode
            return this
        }

        fun filter(filter: MCPToolApprovalFilter?): Builder {
            this.filter = filter
            return this
        }

        fun build(): MCPRequireApproval {
            val mCPRequireApproval: MCPRequireApproval = com.volcengine.ark.runtime.model.responses.tool.mcp.MCPRequireApproval()
            mCPRequireApproval.mode = mode
            mCPRequireApproval.setFilter(filter)
            return mCPRequireApproval
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.mcp.MCPRequireApproval.Builder()
        }
    }
}
