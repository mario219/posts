package com.example.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
internal data class PostsLocal(
    @PrimaryKey
    @ColumnInfo(name = "userId")
    val userId: Int? = null,
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "body")
    val body: String? = null,
    @ColumnInfo(name = "fav")
    var favorite: Boolean = false,
    @ColumnInfo(name = "read")
    var read: Boolean = false
)