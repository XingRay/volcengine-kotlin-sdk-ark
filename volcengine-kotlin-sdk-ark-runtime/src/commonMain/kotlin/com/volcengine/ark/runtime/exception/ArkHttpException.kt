package com.volcengine.ark.runtime.exception

class ArkHttpException(error: ArkAPIError, parent: Exception?, val statusCode: Int, val requestId: String?) : RuntimeException(error.error!!.message, parent) {
    val code: String?

    val param: String?

    val type: String?

    init {
        this.code = error.error!!.code
        this.param = error.error!!.param
        this.type = error.error!!.type
    }

    val message: String?
        get() = this.toString()

    @Override
    fun toString(): String? {
        return "ArkHttpException{" +
                "statusCode=" + statusCode +
                ", message='" + super.getMessage() + '\'' +
                ", code='" + code + '\'' +
                ", param='" + param + '\'' +
                ", type='" + type + '\'' +
                ", requestId='" + requestId + '\'' +
                '}'
    }

    companion object {
        var INTERNAL_SERVICE_CODE: Integer = 500
    }
}
