package com.example.data.service

internal sealed class ApiResultWrapper<out T> {
    data class Success<out T>(val value: T): ApiResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: String? = null): ApiResultWrapper<Nothing>()
    object NetworkError: ApiResultWrapper<Nothing>()
}