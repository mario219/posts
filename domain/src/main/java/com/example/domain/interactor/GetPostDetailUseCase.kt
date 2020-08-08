package com.example.domain.interactor

import com.example.domain.model.Posts
import com.example.domain.repository.PostsRepository
import javax.inject.Inject

class GetPostDetailUseCase @Inject constructor(
    private val repository: PostsRepository
) {

    suspend operator fun invoke(post: String): Posts? {
        return repository.getPost(post)
    }
}