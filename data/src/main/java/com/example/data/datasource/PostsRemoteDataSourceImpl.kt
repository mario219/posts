package com.example.data.datasource

import android.util.Log
import com.example.data.mapper.BaseRemoteMapper
import com.example.data.mapper.PostRemoteMapper
import com.example.data.model.CommentsRemote
import com.example.data.model.UserRemote
import com.example.data.service.PostsApi
import com.example.domain.datasource.PostsRemoteDataSource
import com.example.domain.model.Comments
import com.example.domain.model.Posts
import com.example.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

internal class PostsRemoteDataSourceImpl @Inject constructor(
    private val postsApi: PostsApi,
    private val postsMapper: PostRemoteMapper,
    private val commentsMapper: BaseRemoteMapper<List<CommentsRemote>, List<Comments>>,
    private val userMapper: BaseRemoteMapper<UserRemote, User>
) : PostsRemoteDataSource {

    override suspend fun getPosts(): List<Posts> {
        try {
            val posts = postsApi.getPosts()
            return withContext(Dispatchers.IO) {
                postsMapper.transform(posts)
            }
        } catch (e: HttpException) {
            Log.e("API_ERROR: ", e.message())
        }
        return emptyList()
    }

    override suspend fun getCommentsByPost(postId: String): List<Comments> {
        try {
            val comments = postsApi.getPostComments(postId)
            return withContext(Dispatchers.IO) {
                commentsMapper.transform(comments)
            }
        } catch (e: HttpException) {
            Log.e("API_ERROR: ", e.message())
        }
        return emptyList()
    }

    override suspend fun getPostOwner(userId: String): User? {
        try {
            val user = postsApi.getPostOwner(userId)
            return withContext(Dispatchers.IO) {
                userMapper.transform(user)
            }
        } catch (e: HttpException) {
            Log.e("API_ERROR: ", e.message())
        }
        return null
    }
}