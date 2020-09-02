package com.example.posts

import android.app.Application

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PostsApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}