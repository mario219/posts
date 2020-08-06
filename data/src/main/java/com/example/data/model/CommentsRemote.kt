package com.example.data.model

import com.google.gson.annotations.SerializedName

internal data class CommentsRemote(
    @field:SerializedName("postId") var postId: Int? = null,
    @field:SerializedName("id") var id: Int? = null,
    @field:SerializedName("name") var name: String? = null,
    @field:SerializedName("email") var mail: String? = null,
    @field:SerializedName("body") var body: String? = null
)