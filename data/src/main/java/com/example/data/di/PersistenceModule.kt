package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.persistence.PostsDataBase
import com.example.data.persistence.dao.PostsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object PersistenceModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideDataBase(context: Context): PostsDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            PostsDataBase::class.java,
            "posts.db"
        ).build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun providesPostsDao(dataBase: PostsDataBase): PostsDao {
        return dataBase.postsDao()
    }
}