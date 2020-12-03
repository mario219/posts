package com.example.data.di

import com.example.data.repository.PostsRepositoryImpl
import com.example.domain.repository.PostsRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    @Reusable
    abstract fun providesPostRepository(impl: PostsRepositoryImpl): PostsRepository
}