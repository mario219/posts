package com.example.data.di

import com.example.data.mapper.BaseMapper
import com.example.data.mapper.BaseRemoteMapper
import com.example.data.mapper.CommentsRemoteMapper
import com.example.data.mapper.PostLocalMapper
import com.example.data.mapper.PostRemoteMapper
import com.example.data.mapper.UserRemoteMapper
import com.example.data.model.CommentsRemote
import com.example.data.model.PostsLocal
import com.example.data.model.PostsRemote
import com.example.data.model.UserRemote
import com.example.domain.model.Comments
import com.example.domain.model.Posts
import com.example.domain.model.User
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
internal abstract class MapperModule {

    @Binds
    @Reusable
    abstract fun providesPostLocalMapper(impl: PostLocalMapper): BaseMapper<PostsLocal, Posts>

    @Binds
    @Reusable
    abstract fun providesPostsRemoteMapper(impl: PostRemoteMapper): BaseRemoteMapper<List<PostsRemote>, List<Posts>>

    @Binds
    @Reusable
    abstract fun providesCommentsRemote(impl: CommentsRemoteMapper): BaseRemoteMapper<List<CommentsRemote>, List<Comments>>

    @Binds
    @Reusable
    abstract fun providesUserRemoteMapper(impl: UserRemoteMapper): BaseRemoteMapper<UserRemote, User>
}