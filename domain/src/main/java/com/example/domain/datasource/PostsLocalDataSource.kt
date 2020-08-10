package com.example.domain.datasource

import androidx.paging.DataSource
import com.example.domain.model.Posts

interface PostsLocalDataSource {

    suspend fun getPost(post: String): Posts
    suspend fun savePost(post: Posts)
    fun getPosts(): DataSource.Factory<Int, Posts>
    fun getFavoritePosts(): DataSource.Factory<Int,Posts>
    suspend fun markPostAsFavorite(post: Posts)
    suspend fun unMarkPostFavorite(post: Posts)
    suspend fun markPostAsRead(post: Posts)
    suspend fun removePost(post: Posts)
}