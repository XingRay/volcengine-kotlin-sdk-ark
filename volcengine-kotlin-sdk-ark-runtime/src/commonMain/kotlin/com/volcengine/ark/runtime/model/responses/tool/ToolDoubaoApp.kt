package com.volcengine.ark.runtime.model.responses.tool
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import com.volcengine.ark.runtime.model.responses.tool.doubaoapp.DoubaoAppFeature
import com.volcengine.ark.runtime.model.responses.common.UserLocation

@Serializable
data class ToolDoubaoApp(
    @SerialName("type")
    override val type: String? = ResponsesConstants.TOOL_TYPE_DOUBAO_APP,
    @SerialName("feature")
    val feature: DoubaoAppFeature? = null,
    @SerialName("user_location")
    val userLocation: UserLocation? = null
) : ResponsesTool()
