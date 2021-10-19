package com.example.data.datasource

import com.example.data.di.IoDispatcher
import com.example.data.mapper.BaseRemoteMapper
import com.example.data.mapper.PostRemoteMapper
import com.example.data.model.CommentsRemote
import com.example.data.model.UserRemote
import com.example.data.service.ApiResultWrapper.GenericError
import com.example.data.service.ApiResultWrapper.NetworkError
import com.example.data.service.PostsApi
import com.example.data.service.safeRemoteCall
import com.example.domain.datasource.PostsRemoteDataSource
import com.example.domain.model.Comments
import com.example.domain.model.Posts
import com.example.data.service.ApiResultWrapper.Success
import com.example.data.service.ErrorCodes
import com.example.domain.model.ResultFromRemote
import com.example.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

internal class PostsRemoteDataSourceImpl @Inject constructor(
    private val postsApi: PostsApi,
    private val postsMapper: PostRemoteMapper,
    private val commentsMapper: BaseRemoteMapper<List<CommentsRemote>, List<Comments>>,
    private val userMapper: BaseRemoteMapper<UserRemote, User>,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : PostsRemoteDataSource {

    override suspend fun getPosts(): ResultFromRemote<List<Posts>> {
        var result: ResultFromRemote<List<Posts>>
        safeRemoteCall(ioDispatcher) { postsApi.getPosts() }.run {
            result = when (this) {
                is Success -> ResultFromRemote.Success(postsMapper.transform(value))
                is GenericError -> ResultFromRemote.Error(code, error)
                is NetworkError -> ResultFromRemote.Error(ErrorCodes.SocketTimeOut.code, "Timeout")
            }
        }
        return result
    }

    override suspend fun getCommentsByPost(postId: String): ResultFromRemote<List<Comments>> {
        var result: ResultFromRemote<List<Comments>>
        safeRemoteCall(ioDispatcher) { postsApi.getPostComments(postId) }.run {
            result = when (this) {
                is Success -> ResultFromRemote.Success(commentsMapper.transform(value))
                is GenericError -> ResultFromRemote.Error(code, error)
                is NetworkError -> ResultFromRemote.Error(ErrorCodes.SocketTimeOut.code, "Timeout")
            }
        }
        return result
    }

    override suspend fun getPostOwner(userId: String): ResultFromRemote<User> {
        var result: ResultFromRemote<User>
        safeRemoteCall(ioDispatcher) { postsApi.getPostOwner(userId) }.run {
            result = when (this) {
                is Success -> ResultFromRemote.Success(userMapper.transform(value))
                is GenericError -> ResultFromRemote.Error(code, error)
                is NetworkError -> ResultFromRemote.Error(ErrorCodes.SocketTimeOut.code, "Timeout")
            }
        }
        return result
    }
}