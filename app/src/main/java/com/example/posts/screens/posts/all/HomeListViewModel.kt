package com.example.posts.screens.posts.all

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Config
import androidx.paging.toLiveData
import com.example.domain.interactor.LoadAllPostsUseCase
import com.example.domain.interactor.RetrievePostsFromRemoteUseCase
import com.example.posts.utils.NetworkUtils
import com.example.posts.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeListViewModel @Inject constructor(
    loadAllPosts: LoadAllPostsUseCase,
    retrievePostsFromRemote: RetrievePostsFromRemoteUseCase,
    private val network: NetworkUtils
) : ViewModel() {

    private val pageConfig = Config(
        pageSize = 20,
        enablePlaceholders = true,
        maxSize = 100
    )
    val postList = loadAllPosts().toLiveData(pageConfig)

    private val _askInternetConnection = SingleLiveEvent<Boolean>()
    val askForInternet: LiveData<Boolean>
        get() = _askInternetConnection

    init {
        viewModelScope.launch {
            retrievePostsFromRemote()
        }
    }

    companion object {
        private const val DELAY_FOR_ASKING_INTERNET = 10000L
    }
}