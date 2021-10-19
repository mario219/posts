package com.example.domain.interactor

import com.example.domain.repository.PostsRepository
import javax.inject.Inject

class RetrievePostsFromRemoteUseCase @Inject constructor(
    private val repository: PostsRepository
) {

    suspend operator fun invoke(): String {
        return if (repository.getPostsFromRemote()) {
            ""
        } else {
            "Something happened when fetching new content"
        }
    }
}