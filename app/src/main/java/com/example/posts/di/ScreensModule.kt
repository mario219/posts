package com.example.posts.di

import com.example.data.di.RepositoryModule
import com.example.posts.screens.PostsActivity
import com.example.posts.screens.detail.DetailModule
import com.example.posts.screens.posts.all.HomeListModule
import com.example.posts.screens.posts.fav.FavListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Include here the fragmentModule set that integrates the host,
 * also include here the dependency module for this feature
 */
@Module(includes = [
    RepositoryModule::class,
    HomeListModule::class,
    FavListModule::class,
    DetailModule::class
])
/**
 * Represent a consolidate of features or just a feature of the app
 * For this example we are calling it just ScreensModule
 */
abstract class ScreensModule {

    /**
     *  Activity that hosts all fragments in this example
     *  You can configure as many module as you want for activity hosting its fragments
     */
    @ContributesAndroidInjector(modules = [ViewModelBuilderModule::class])
    internal abstract fun postsActivity(): PostsActivity
}