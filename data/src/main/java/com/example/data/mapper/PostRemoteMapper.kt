package com.example.data.mapper

import com.example.data.model.PostsRemote
import com.example.domain.model.Posts
import javax.inject.Inject

internal class PostRemoteMapper @Inject constructor(
) : BaseRemoteMapper<List<@JvmSuppressWildcards PostsRemote>, List<@JvmSuppressWildcards Posts>> {

    override fun transform(input: List<PostsRemote>): List<Posts> {
        val posts = mutableListOf<Posts>()
        input.forEach {
            posts.add(transformPost(it))
        }
        return posts
    }

    fun transformPost(input: PostsRemote): Posts {
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