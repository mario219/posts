package com.example.data.repository

import androidx.paging.DataSource
import com.example.domain.repository.PostsRepository
import com.example.domain.datasource.PostsLocalDataSource
import com.example.domain.datasource.PostsRemoteDataSource
import com.example.domain.model.Comments
import com.example.domain.model.Posts
import com.example.domain.model.User
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val localDataSource: PostsLocalDataSource,
    private val remoteDataSource: PostsRemoteDataSource
) : PostsRepository {

    override suspend fun getPost(post: String): Posts {
        return localDataSource.getPost(post)
    }

    override suspend fun getPostsFromRemote() {
        remoteDataSource.getPosts().map {
            localDataSource.savePost(it)
        }
    }

    override fun loadPosts(): DataSource.Factory<Int, Posts> {
        return localDataSource.getPosts()
    }

    override fun loadFavoritePosts(): DataSource.Factory<Int, Posts> {
        return localDataSource.getFavoritePosts()
    }

    override suspend fun markPostFavorite(post: Posts) {
        localDataSource.markPostAsFavorite(post)
    }

    override suspend fun unMarkPostAsFavorite(post: Posts) {
        localDataSource.unMarkPostFavorite(post)
    }

    override suspend fun markPostAsRead(post: Posts) {
        localDataSource.markPostAsRead(post)
    }

    override suspend fun getCommentsByPost(postId: String): List<Comments> {
        return remoteDataSource.getCommentsByPost(postId)
    }

    override suspend fun getPostOwner(userId: String): User? {
        return remoteDataSource.getPostOwner(userId)
    }
}