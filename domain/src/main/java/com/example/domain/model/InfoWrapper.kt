package com.example.domain.model

data class InfoWrapper(
    var post: Posts? = null,
    var comments: List<Comments>? = null,
    var user: User? = null
)