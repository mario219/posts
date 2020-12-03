package com.example.data.di

import com.example.data.datasource.PostsLocalDataSourceImpl
import com.example.data.datasource.PostsRemoteDataSourceImpl
import com.example.domain.datasource.PostsLocalDataSource
import com.example.domain.datasource.PostsRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
internal abstract class DataSourceModule {

    @Binds
    @Reusable
    abstract fun providesPostLocalDataSource(impl: PostsLocalDataSourceImpl): PostsLocalDataSource

    @Binds
    @Reusable
    abstract fun providesPostRemoteDataSource(impl: PostsRemoteDataSourceImpl): PostsRemoteDataSource
}