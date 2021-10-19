package com.example.posts.di

import android.content.Context
import com.example.posts.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
class UtilsModule {

    @Provides
    @Reusable
    fun providesNetworkUtils(@ApplicationContext appContext: Context): NetworkUtils {
        return NetworkUtils(appContext)
    }
}