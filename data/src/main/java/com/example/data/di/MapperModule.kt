package com.example.data.di

import com.example.data.mapper.*
import com.example.data.mapper.BaseMapper
import com.example.data.mapper.BaseRemoteMapper
import com.example.data.mapper.CommentsRemoteMapper
import com.example.data.mapper.PostLocalMapper
import com.example.data.mapper.PostRemoteMapper
import com.example.data.model.CommentsRemote
import com.example.data.model.PostsLocal
import com.example.data.model.PostsRemote
import com.example.data.model.UserRemote
import com.example.domain.model.Comments
import com.example.domain.model.Posts
import com.example.domain.model.User
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class MapperModule {

    @Binds
    @Singleton
    abstract fun providesPostLocalMapper(impl: PostLocalMapper): BaseMapper<PostsLocal, Posts>

    @Binds
    @Singleton
    abstract fun providesPostsRemoteMapper(impl: PostRemoteMapper): BaseRemoteMapper<PostsRemote, Posts>

    @Binds
    @Singleton
    abstract fun providesCommentsRemote(impl: CommentsRemoteMapper): BaseRemoteMapper<CommentsRemote, Comments>

    @Binds
    @Singleton
    abstract fun providesUserRemoteMapper(impl: UserRemoteMapper): BaseRemoteMapper<UserRemote, User>
}