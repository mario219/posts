package com.example.data.service

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

internal suspend fun <T> safeRemoteCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): ApiResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ApiResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ApiResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse =  throwable.message()
                    ApiResultWrapper.GenericError(code, errorResponse)
                }
                else -> {
                    ApiResultWrapper.GenericError(null, null)
                }
            }
        }
    }
}

