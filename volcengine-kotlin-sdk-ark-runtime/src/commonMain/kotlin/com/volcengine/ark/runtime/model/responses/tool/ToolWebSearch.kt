package com.volcengine.ark.runtime.model.responses.tool
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import com.volcengine.ark.runtime.model.responses.common.UserLocation

@Serializable
data class ToolWebSearch(
    @SerialName("type")
    override val type: String? = ResponsesConstants.TOOL_TYPE_WEB_SEARCH,
    @SerialName("limit")
    val limit: Long? = null,
    @SerialName("user_location")
    val userLocation: UserLocation? = null,
    @SerialName("sources")
    val sources: List<String?>? = null
) : ResponsesTool()