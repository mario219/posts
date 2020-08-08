package com.example.domain.model

class Posts(
    val userId: Int? = null,
    val id: Int? = null,
    val title: String? = null,
    val body: String? = null,
    var favorite: Boolean = false,
    var read: Boolean = false
)