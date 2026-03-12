package com.volcengine.ark.runtime.exception

import kotlin.jvm.JvmField


class ArkAPIError {
    @JvmField
    var error: ArkErrorDetails? = null

    constructor(error: ArkErrorDetails?) {
        this.error = error
    }

    constructor()

    @Override
    fun toString(): String? {
        return "ArkAPIError{" +
                "error=" + error +
                '}'
    }

    class ArkErrorDetails {
        @JvmField
        var message: String? = null

        @JvmField
        var type: String? = null

        @JvmField
        var param: String? = null

        @JvmField
        var code: String? = null

        constructor(message: String?, type: String?, param: String?, code: String?) {
            this.message = message
            this.type = type
            this.param = param
            this.code = code
        }

        constructor()

        @Override
        fun toString(): String? {
            return "ArkErrorDetails{" +
                    "message='" + message + '\'' +
                    ", type='" + type + '\'' +
                    ", param='" + param + '\'' +
                    ", code='" + code + '\'' +
                    '}'
        }
    }
}
