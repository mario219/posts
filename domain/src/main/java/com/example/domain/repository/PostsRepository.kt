package com.example.domain.repository

import androidx.paging.DataSource
import com.example.domain.model.Comments
import com.example.domain.model.Posts
import com.example.domain.model.User

interface PostsRepository {

    suspend fun getPost(post: String): Posts
    suspend fun getPostsFromRemote()
    fun loadPosts(): DataSource.Factory<Int, Posts>
    fun loadFavoritePosts(): DataSource.Factory<Int, Posts>
    suspend fun unMarkPostAsFavorite(post: Posts)
    suspend fun markPostFavorite(post: Posts)
    suspend fun markPostAsRead(post: Posts)
    suspend fun getCommentsByPost(postId: String): List<Comments>
    suspend fun getPostOwner(userId: String): User?
}