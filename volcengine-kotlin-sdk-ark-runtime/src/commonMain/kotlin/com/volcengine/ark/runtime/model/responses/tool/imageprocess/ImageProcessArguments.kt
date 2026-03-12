package com.volcengine.ark.runtime.model.responses.tool.imageprocess
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ImageProcessArguments {
    @SerialName("image_index")
    private var imageIndex: Int? = null

    @SerialName("points")
    var points: String? = null

    @SerialName("draw_line")
    var drawLine: Boolean? = null

    @SerialName("bbox_str")
    var bboxStr: String? = null

    @SerialName("crop")
    var crop: Boolean? = null

    @SerialName("degree")
    private var degree: Int? = null

    @SerialName("scale")
    var scale: Double? = null

    fun getImageIndex(): Int? {
        return imageIndex
    }

    fun setImageIndex(imageIndex: Int?) {
        this.imageIndex = imageIndex
    }

    fun getDegree(): Int? {
        return degree
    }

    fun setDegree(degree: Int?) {
        this.degree = degree
    }
}