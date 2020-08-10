package com.example.domain.interactor

import androidx.paging.DataSource
import com.example.domain.model.Posts
import com.example.domain.repository.PostsRepository
import javax.inject.Inject

class LoadFavPostsUseCase @Inject constructor(
    private val repository: PostsRepository
) {

    operator fun invoke(): DataSource.Factory<Int, Posts> {
        return repository.loadFavoritePosts()
    }
}