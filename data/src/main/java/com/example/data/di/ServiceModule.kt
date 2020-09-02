package com.example.data.di

import com.example.data.service.PostsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
internal class ServiceModule {

    @Provides
    @Singleton
    fun providesPostsApi(): PostsApi {
        return ServiceFactory.getService(PostsApi::class.java)
    }
}