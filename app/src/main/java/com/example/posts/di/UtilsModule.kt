package com.example.posts.di

import android.content.Context
import com.example.posts.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object UtilsModule {

    @JvmStatic
    @Provides
    @Singleton
    fun providesNetworkUtils(context: Context): NetworkUtils {
        return NetworkUtils(context)
    }
}