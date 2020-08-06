package com.example.data.di

import com.example.data.datasource.PostsLocalDataSourceImpl
import com.example.data.datasource.PostsRemoteDataSourceImpl
import com.example.domain.datasource.PostsLocalDataSource
import com.example.domain.datasource.PostsRemoteDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [
    ServiceModule::class,
    PersistenceModule::class,
    MapperModule::class
])
internal abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun providesPostLocalDataSource(impl: PostsLocalDataSourceImpl): PostsLocalDataSource

    @Binds
    @Singleton
    abstract fun providesPostRemoteDataSource(impl: PostsRemoteDataSourceImpl): PostsRemoteDataSource
}