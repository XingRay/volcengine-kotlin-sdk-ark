package com.volcengine.ark.runtime.model.responses.tool
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class ToolImageProcess : ResponsesTool(ResponsesConstants.TOOL_TYPE_IMAGE_PROCESS) {
    @SerialName("point")
    private var point: ImageProcessPointOptions? = null

    @SerialName("grounding")
    private var grounding: ImageProcessGroundingOptions? = null

    @SerialName("zoom")
    private var zoom: ImageProcessZoomOptions? = null

    @SerialName("rotate")
    private var rotate: ImageProcessRotateOptions? = null

    fun getPoint(): ImageProcessPointOptions? {
        return point
    }

    fun setPoint(point: ImageProcessPointOptions?) {
        this.point = point
    }

    fun getGrounding(): ImageProcessGroundingOptions? {
        return grounding
    }

    fun setGrounding(grounding: ImageProcessGroundingOptions?) {
        this.grounding = grounding
    }

    fun getZoom(): ImageProcessZoomOptions? {
        return zoom
    }

    fun setZoom(zoom: ImageProcessZoomOptions?) {
        this.zoom = zoom
    }

    fun getRotate(): ImageProcessRotateOptions? {
        return rotate
    }

    fun setRotate(rotate: ImageProcessRotateOptions?) {
        this.rotate = rotate
    }


    class Builder {
        private var point: ImageProcessPointOptions? = null
        private var grounding: ImageProcessGroundingOptions? = null
        private var zoom: ImageProcessZoomOptions? = null
        private var rotate: ImageProcessRotateOptions? = null

        fun point(point: ImageProcessPointOptions?): Builder {
            this.point = point
            return this
        }

        fun grounding(grounding: ImageProcessGroundingOptions?): Builder {
            this.grounding = grounding
            return this
        }

        fun zoom(zoom: ImageProcessZoomOptions?): Builder {
            this.zoom = zoom
            return this
        }

        fun rotate(rotate: ImageProcessRotateOptions?): Builder {
            this.rotate = rotate
            return this
        }

        fun build(): ToolImageProcess {
            val toolImageProcess: ToolImageProcess = com.volcengine.ark.runtime.model.responses.tool.ToolImageProcess()
            toolImageProcess.setPoint(point)
            toolImageProcess.setGrounding(grounding)
            toolImageProcess.setZoom(zoom)
            toolImageProcess.setRotate(rotate)
            return toolImageProcess
        }
    }

    companion object {
        fun builder(): Builder {
            return com.volcengine.ark.runtime.model.responses.tool.ToolImageProcess.Builder()
        }
    }
}