package com.example.data.mapper

internal interface BaseMapper<T, F> {
    fun transform(input: T): F
    fun transformToEntity(input: F): T
}

internal interface BaseRemoteMapper<T, F> {
    fun transform(input: T): F
}