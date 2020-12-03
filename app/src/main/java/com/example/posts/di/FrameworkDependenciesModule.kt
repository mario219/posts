package com.example.posts.di

import android.content.Context
import android.content.SharedPreferences
import com.example.posts.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object FrameworkDependenciesModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences(BuildConfig.PREFS, Context.MODE_PRIVATE)
    }
}