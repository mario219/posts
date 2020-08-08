package com.example.posts.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.interactor.GetCommentsUseCase
import com.example.domain.interactor.GetPostDetailUseCase
import com.example.domain.interactor.GetPostOwnerUseCase
import com.example.domain.model.Comments
import com.example.domain.model.InfoWrapper
import com.example.domain.model.Posts
import com.example.domain.model.User
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getPostOwner: GetPostOwnerUseCase,
    private val getPostComments: GetCommentsUseCase,
    private val getPostDetail: GetPostDetailUseCase
) : ViewModel() {

    private val _post = MutableLiveData<Posts>()
    private val _user = MutableLiveData<User>()
    private val _comments = MutableLiveData<List<Comments>>()
    private val _infoWrapper = MediatorLiveData<InfoWrapper>()
    val info: LiveData<InfoWrapper>
        get() = _infoWrapper

    init {
        startWrappingData()
    }

    fun init(post: String, user: String) {
        viewModelScope.launch {
            _post.postValue(getPostDetail(post))
            _user.value = getPostOwner(user)
            _comments.postValue(getPostComments(post))
        }
    }

    private fun startWrappingData() {
        val infoWrapper = InfoWrapper()
        _infoWrapper.addSource(_user) {
            infoWrapper.user = it
            _infoWrapper.postValue(infoWrapper)
        }
        _infoWrapper.addSource(_comments) {
            infoWrapper.comments = it
            _infoWrapper.postValue(infoWrapper)
        }
        _infoWrapper.addSource(_post) {
            infoWrapper.post = it
            _infoWrapper.postValue(infoWrapper)
        }
    }
}