package com.example.data.model

import com.google.gson.annotations.SerializedName

internal data class PostsRemote(
    @field:SerializedName("userId") var userId: Int? = null,
    @field:SerializedName("id") var id: Int? = null,
    @field:SerializedName("title") var title: String? = null,
    @field:SerializedName("body") var body: String? = null
)
