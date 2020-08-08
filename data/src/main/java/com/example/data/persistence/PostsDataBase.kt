package com.example.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.PostsLocal
import com.example.data.persistence.dao.PostsDao

@Database(
    entities = [
        PostsLocal::class
    ],
    version = 2,
    exportSchema = false
)
internal abstract class PostsDataBase : RoomDatabase() {

    abstract fun postsDao(): PostsDao
}