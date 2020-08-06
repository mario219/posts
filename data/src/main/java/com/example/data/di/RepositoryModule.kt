package com.example.data.di

import com.example.data.repository.PostsRepositoryImpl
import com.example.domain.repository.PostsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [DataSourceModule::class])
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun providesPostRepository(impl: PostsRepositoryImpl): PostsRepository
}