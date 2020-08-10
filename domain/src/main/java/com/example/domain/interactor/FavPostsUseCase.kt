package com.example.domain.interactor

import com.example.domain.model.Posts
import com.example.domain.repository.PostsRepository
import javax.inject.Inject

class FavPostsUseCase @Inject constructor(
    private val repository: PostsRepository
) {

    suspend fun mark(post: Posts) {
        repository.markPostFavorite(post)
    }

    suspend fun unMark(post: Posts) {
        repository.unMarkPostAsFavorite(post)
    }
}