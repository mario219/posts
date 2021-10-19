package com.example.domain.interactor

import com.example.domain.model.ResultFromRemote.Error
import com.example.domain.model.ResultFromRemote.Success
import com.example.domain.model.User
import com.example.domain.repository.PostsRepository
import javax.inject.Inject

class GetPostOwnerUseCase @Inject constructor(
    private val repository: PostsRepository
) {

    suspend operator fun invoke(user: String): User? {
        return when (val owner = repository.getPostOwner(user)) {
            is Success -> owner.value
            is Error -> null
        }
    }
}