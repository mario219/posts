package com.example.data.service

import com.example.data.model.CommentsRemote
import com.example.data.model.PostsRemote
import com.example.data.model.UserRemote
import retrofit2.http.GET
import retrofit2.http.Path

internal interface PostsApi {

    @GET("posts")
    suspend fun getPosts(): List<PostsRemote>

    @GET("posts/{post}/comments")
    suspend fun getPostComments(
        @Path("post") postId: String
    ): List<CommentsRemote>

    @GET("users/{userId}")
    suspend fun getPostOwner(
        @Path("userId") userId: String
    ): UserRemote
}