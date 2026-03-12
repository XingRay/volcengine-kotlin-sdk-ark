package com.volcengine.ark.runtime.model.responses.tool.imageprocess
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class ImageProcessArguments(
    @SerialName("image_index")
    val imageIndex: Int? = null,

    @SerialName("points")
    val points: String? = null,

    @SerialName("draw_line")
    val drawLine: Boolean? = null,

    @SerialName("bbox_str")
    val bboxStr: String? = null,

    @SerialName("crop")
    val crop: Boolean? = null,

    @SerialName("degree")
    val degree: Int? = null,

    @SerialName("scale")
    val scale: Double? = null
)