package io.github.xingray.volcengine_kotlin_sdk_ark

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

sealed class ApiResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : ApiResult<T>()

    data class Error<out T : Any>(val code: Int = -1, val message: String = "", val error: Throwable? = null) : ApiResult<T>() {
        fun <R : Any> toApiError(): ApiResult<R> {
            return Error(code = code, message = message, error = error)
        }

        fun <R : Any> toApiError(code: Int): ApiResult<R> {
            return Error(code = code, message = message, error = error)
        }
    }

    companion object {
        fun <R : Any> success(data: R): Success<R> {
            return Success(data)
        }

        fun <R : Any> error(code: Int, message: String = "", error: Throwable? = null): Error<R> {
            return Error(code, message, error = error)
        }

        fun <R : Any> error(code: Int, message: String = ""): Error<R> {
            return Error(code, message, null)
        }

        fun <R : Any> error(code: Int): Error<R> {
            return Error(code, "", null)
        }

        fun <R : Any> error(message: String): Error<R> {
            return Error(-1, message, null)
        }

        fun <R : Any> error(error: Throwable): Error<R> {
            return Error(-1, error.stackTraceToString(), error)
        }

        fun <R : Any> notImplemented(): Error<R> {
            return Error(-1, "功能未实现", null)
        }
    }
}

@OptIn(ExperimentalContracts::class)
fun <R : Any> ApiResult<R>.isError(): Boolean {
    contract {
        returns(true) implies (this@isError is ApiResult.Error<R>)
        returns(false) implies (this@isError is ApiResult.Success<R>)
    }
    return this@isError is ApiResult.Error<R>
}

@OptIn(ExperimentalContracts::class)
fun <R : Any> ApiResult<R>.isSuccess(): Boolean {
    contract {
        returns(true) implies (this@isSuccess is ApiResult.Success<R>)
        returns(false) implies (this@isSuccess is ApiResult.Error<R>)
    }
    return this@isSuccess is ApiResult.Success<R>
}

fun <R : Any> ApiResult<R>.dataOrNull(): R? {
    return when (this) {
        is ApiResult.Success<R> -> {
            data
        }

        is ApiResult.Error<R> -> {
            null
        }
    }
}