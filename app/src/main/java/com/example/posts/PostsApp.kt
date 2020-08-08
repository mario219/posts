package com.example.posts

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class PostsApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        TODO("Not yet implemented")
    }
}