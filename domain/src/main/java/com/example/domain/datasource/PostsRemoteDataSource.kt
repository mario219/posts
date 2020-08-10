package com.example.domain.datasource

import com.example.domain.model.Comments
import com.example.domain.model.Posts
import com.example.domain.model.User

interface PostsRemoteDataSource {

    suspend fun getPosts(): List<Posts>
    suspend fun getCommentsByPost(postId: String): List<Comments>
    suspend fun getPostOwner(userId: String): User?
}