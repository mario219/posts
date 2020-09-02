package com.example.posts.di

import com.example.data.di.RepositoryModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module(includes = [
    RepositoryModule::class,
    FrameworkDependencies::class,
    UtilsModule::class
])
@InstallIn(ApplicationComponent::class)
object AppModule