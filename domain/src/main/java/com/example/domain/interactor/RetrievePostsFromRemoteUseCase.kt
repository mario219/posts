package com.example.domain.interactor

import com.example.domain.repository.PostsRepository
import javax.inject.Inject

class RetrievePostsFromRemoteUseCase @Inject constructor(
    private val repository: PostsRepository
) {

    suspend operator fun invoke() {
        repository.getPostsFromRemote()
    }
}