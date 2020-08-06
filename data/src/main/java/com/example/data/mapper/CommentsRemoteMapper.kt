package com.example.data.mapper

import com.example.data.model.CommentsRemote
import com.example.domain.model.Comments
import javax.inject.Inject

internal class CommentsRemoteMapper @Inject constructor() : BaseRemoteMapper<CommentsRemote, Comments> {

    override fun transform(input: CommentsRemote): Comments {
        return Comments(
            postId = input.postId,
            id = input.id,
            name = input.name,
            mail = input.mail,
            body = input.body
        )
    }
}