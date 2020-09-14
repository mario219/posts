package com.example.posts.screens.posts.all

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Config
import androidx.paging.toLiveData
import com.example.domain.DATA_FETCHED
import com.example.domain.interactor.LoadAllPostsUseCase
import com.example.domain.interactor.LoadFavPostsUseCase
import com.example.domain.interactor.RemovePostUseCase
import com.example.domain.interactor.RetrievePostsFromRemoteUseCase
import com.example.domain.model.Posts
import com.example.posts.utils.NetworkUtils
import com.example.posts.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class HomeListViewModel @ViewModelInject constructor(
    loadAllPosts: LoadAllPostsUseCase,
    private val retrievePostsFromRemote: RetrievePostsFromRemoteUseCase,
    private val network: NetworkUtils,
    private val prefs: SharedPreferences,
    loadFavPosts: LoadFavPostsUseCase,
    private val removePostAt: RemovePostUseCase
) : ViewModel() {

    private val pageConfig = Config(
        pageSize = 20,
        enablePlaceholders = false,
        maxSize = 100
    )
    val postList = loadAllPosts().toLiveData(pageConfig)
    val favList = loadFavPosts().toLiveData(pageConfig)

    private val _askInternetConnection = SingleLiveEvent<Boolean>()
    val askForInternet: LiveData<Boolean>
        get() = _askInternetConnection

    init {
        viewModelScope.launch {
            val dataFetched = prefs.getBoolean(DATA_FETCHED, false)
            if (dataFetched.not()) {
                retrievePostsFromRemote()
                prefs.edit().putBoolean(DATA_FETCHED, true).apply()
            }
        }
    }

    fun fetchDataAgain() {
        viewModelScope.launch {
            retrievePostsFromRemote()
        }
    }

    fun removePostOnSwipe(post: Posts) {
        viewModelScope.launch {
            removePostAt(post)
        }
    }

    companion object {
        private const val DELAY_FOR_ASKING_INTERNET = 10000L
    }
}