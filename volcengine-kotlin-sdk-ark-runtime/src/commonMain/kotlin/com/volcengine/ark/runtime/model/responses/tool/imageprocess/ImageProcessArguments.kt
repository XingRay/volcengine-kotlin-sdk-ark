package com.volcengine.ark.runtime.model.responses.tool.imageprocess

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ImageProcessArguments {
    @JsonProperty("image_index")
    private var imageIndex: Integer? = null

    @JsonProperty("points")
    var points: String? = null

    @JsonProperty("draw_line")
    var drawLine: Boolean? = null

    @JsonProperty("bbox_str")
    var bboxStr: String? = null

    @JsonProperty("crop")
    var crop: Boolean? = null

    @JsonProperty("degree")
    private var degree: Integer? = null

    @JsonProperty("scale")
    var scale: Double? = null

    fun getImageIndex(): Integer? {
        return imageIndex
    }

    fun setImageIndex(imageIndex: Integer?) {
        this.imageIndex = imageIndex
    }

    fun getDegree(): Integer? {
        return degree
    }

    fun setDegree(degree: Integer?) {
        this.degree = degree
    }
}