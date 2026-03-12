package com.volcengine.ark.runtime.model.responses.tool
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import com.volcengine.ark.runtime.model.responses.constant.ResponsesConstants
import com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessPointOptions
import com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessGroundingOptions
import com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessZoomOptions
import com.volcengine.ark.runtime.model.responses.tool.imageprocess.ImageProcessRotateOptions

@Serializable
data class ToolImageProcess(
    @SerialName("type")
    override val type: String? = ResponsesConstants.TOOL_TYPE_IMAGE_PROCESS,
    @SerialName("point")
    val point: ImageProcessPointOptions? = null,
    @SerialName("grounding")
    val grounding: ImageProcessGroundingOptions? = null,
    @SerialName("zoom")
    val zoom: ImageProcessZoomOptions? = null,
    @SerialName("rotate")
    val rotate: ImageProcessRotateOptions? = null
) : ResponsesTool()