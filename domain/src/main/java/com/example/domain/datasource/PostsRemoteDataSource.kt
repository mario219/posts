package com.example.domain.datasource

import com.example.domain.model.Comments
import com.example.domain.model.Posts
import com.example.domain.model.ResultFromRemote
import com.example.domain.model.User

interface PostsRemoteDataSource {

    suspend fun getPosts(): ResultFromRemote<List<Posts>>
    suspend fun getCommentsByPost(postId: String): ResultFromRemote<List<Comments>>
    suspend fun getPostOwner(userId: String): ResultFromRemote<User>
}