package com.example.domain.interactor

import com.example.domain.model.Comments
import com.example.domain.repository.PostsRepository
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val repository: PostsRepository
) {

    suspend operator fun invoke(post: String): List<Comments> {
        return repository.getCommentsByPost(post)
    }
}