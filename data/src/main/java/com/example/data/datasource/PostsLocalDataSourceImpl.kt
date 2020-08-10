package com.example.data.datasource

import androidx.paging.DataSource
import com.example.data.mapper.BaseMapper
import com.example.data.model.PostsLocal
import com.example.data.persistence.dao.PostsDao
import com.example.domain.datasource.PostsLocalDataSource
import com.example.domain.model.Posts
import javax.inject.Inject

internal class PostsLocalDataSourceImpl @Inject constructor(
    private val postsDao: PostsDao,
    private val mapper: BaseMapper<PostsLocal, Posts>
) : PostsLocalDataSource {

    override suspend fun savePost(post: Posts) {
        postsDao.insert(mapper.transformToEntity(post))
    }

    override suspend fun getPost(post: String): Posts {
        return mapper.transform(postsDao.getPost(post.toInt()))
    }

    override fun getPosts(): DataSource.Factory<Int, Posts> {
        return postsDao.getPosts().map {
            mapper.transform(it)
        }
    }

    override fun getFavoritePosts(): DataSource.Factory<Int,Posts> {
        return postsDao.getFavoritePosts().map {
            mapper.transform(it)
        }
    }

    override suspend fun unMarkPostFavorite(post: Posts) {
        post.favorite = false
        postsDao.insert(mapper.transformToEntity(post))
    }

    override suspend fun markPostAsFavorite(post: Posts) {
        post.favorite = true
        postsDao.insert(mapper.transformToEntity(post))
    }

    override suspend fun markPostAsRead(post: Posts) {
        post.read = true
        postsDao.insert(mapper.transformToEntity(post))
    }
}