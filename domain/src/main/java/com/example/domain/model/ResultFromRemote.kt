package com.example.domain.model

sealed class ResultFromRemote<out T> {
    data class Success<out T>(val value: T): ResultFromRemote<T>()
    data class Error(val code: Int? = null, val error: String? = null): ResultFromRemote<Nothing>()
}