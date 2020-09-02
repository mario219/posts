package com.example.data.di

import com.example.data.repository.PostsRepositoryImpl
import com.example.domain.repository.PostsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module(includes = [DataSourceModule::class])
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesPostRepository(impl: PostsRepositoryImpl): PostsRepository
}