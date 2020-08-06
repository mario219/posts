package com.example.data.mapper

import com.example.data.model.PostsLocal
import com.example.domain.model.Posts
import javax.inject.Inject

internal class PostLocalMapper @Inject constructor() : BaseMapper<PostsLocal, Posts> {

    override fun transform(input: PostsLocal): Posts {
        return Posts(
            userId = input.userId,
            id = input.id,
            title = input.title,
            body = input.body,
            favorite = input.favorite,
            read = input.read
        )
    }

    override fun transformToEntity(input: Posts): PostsLocal {
        return PostsLocal(
            userId = input.userId,
            id = input.id,
            title = input.title,
            body = input.body,
            favorite = input.favorite,
            read = input.read
        )
    }
}