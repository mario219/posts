package com.example.data.mapper

import com.example.data.model.PostsRemote
import com.example.domain.model.Posts
import javax.inject.Inject

internal class PostRemoteMapper @Inject constructor() : BaseRemoteMapper<PostsRemote, Posts> {

    override fun transform(input: PostsRemote): Posts {
        return Posts(
            userId = input.userId,
            id = input.id,
            title = input.title,
            body = input.body,
            favorite = false,
            read = false
        )
    }
}