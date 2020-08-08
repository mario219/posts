package com.example.data.mapper

import com.example.data.model.CommentsRemote
import com.example.domain.model.Comments
import javax.inject.Inject

internal class CommentsRemoteMapper @Inject constructor(
) : BaseRemoteMapper<List<@JvmSuppressWildcards CommentsRemote>, List<@JvmSuppressWildcards Comments>> {

    override fun transform(input: List<CommentsRemote>): List<Comments> {
        val comments = mutableListOf<Comments>()
        input.forEach {
            comments.add(transformComment(it))
        }
        return comments
    }

    private fun transformComment(input: CommentsRemote): Comments {
        return Comments(
            postId = input.postId,
            id = input.id,
            name = input.name,
            mail = input.mail,
            body = input.body
        )
    }
}