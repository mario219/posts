package com.example.posts.di

import android.content.Context
import com.example.posts.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UtilsModule {

    @JvmStatic
    @Provides
    @Singleton
    fun providesNetworkUtils(@ApplicationContext appContext: Context): NetworkUtils {
        return NetworkUtils(appContext)
    }
}