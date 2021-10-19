package com.example.data.persistence.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.example.data.model.PostsLocal

@Dao
internal interface PostsDao : BaseDao<PostsLocal> {

    @Query("SELECT * FROM posts WHERE id = :posts")
    suspend fun getPost(posts: Int): PostsLocal

    @Query("SELECT * FROM posts")
    fun getPosts(): DataSource.Factory<Int, PostsLocal>

    @Query("SELECT * FROM posts WHERE fav = 1")
    fun getFavoritePosts(): DataSource.Factory<Int, PostsLocal>

    @Query("DELETE FROM posts")
    fun deleteAllPosts()
}