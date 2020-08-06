package com.example.data.persistence.dao

import androidx.paging.DataSource
import androidx.room.Query
import com.example.data.model.PostsLocal

internal interface PostsDao : BaseDao<PostsLocal> {

    @Query("SELECT * FROM posts ORDER BY title DESC")
    fun getPosts(): DataSource.Factory<Int, PostsLocal>

    @Query("SELECT * FROM posts WHERE fav = 1")
    fun getFavoritePosts(): DataSource.Factory<Int, PostsLocal>
}