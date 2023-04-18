package com.example.weatheralertapp.utils

import android.util.Log
import okhttp3.Response

sealed class Resource<out T : Any?> {

    class Success<out T : Any?>(val data: T) : Resource<T>()

    sealed class Error<out T : Any> : Resource<T>() {
        class ServerError<out T : Any>(
            val error: String?,
            val errorCode: Int = INTERNAL_ERROR_CODE
        ) : Error<T>() {

            companion object {
                const val INTERNAL_ERROR_CODE = -1
            }

            constructor(errorResponse: ServerError<Any>) : this(
                errorResponse.error,
                errorResponse.errorCode
            )
        }

        class InternalServerError<out T : Any> : Error<T>()
    }
}

suspend fun <T : Any, R : Any?> handleRequest(
    requestFunc: suspend () -> T,
    convertRsp: suspend (T) -> R
): Resource<R>? {
    return when (val response = catchRequestResponse(requestFunc)) {
        is Resource.Success ->
            Resource.Success(convertRsp(response.data))

        is Resource.Error.ServerError ->
            Resource.Error.ServerError(response)

        is Resource.Error.InternalServerError ->
            Resource.Error.InternalServerError()

        else -> null
    }
}

suspend fun <T : Any> catchRequestResponse(
    requestFunc: suspend () -> T,
): Resource<T>? {
    return try {
        val response = requestFunc.invoke()
        if (response is Response && response.code > 500) {
            return Resource.Error.InternalServerError()
        }
        if (response is Response && response.code != 200) {
            return Resource.Error.ServerError(response.message)
        }
        Resource.Success(response)
    } catch (ex: Exception) {
        ex.localizedMessage?.let { Log.e("exception", it) }
        null
    }
}