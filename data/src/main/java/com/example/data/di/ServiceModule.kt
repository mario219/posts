package com.example.data.di

import com.example.data.service.PostsApi
import dagger.Module
import dagger.Provides

@Module
internal class ServiceModule {

    @Provides
    fun providesPostsApi(): PostsApi {
        return ServiceFactory.getService(PostsApi::class.java)
    }
}