package com.example.posts.screens.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.interactor.GetCommentsUseCase
import com.example.domain.interactor.GetPostDetailUseCase
import com.example.domain.interactor.GetPostOwnerUseCase
import com.example.domain.interactor.MarkAsReadUseCase
import com.example.domain.interactor.FavPostsUseCase
import com.example.domain.model.Comments
import com.example.domain.model.InfoWrapper
import com.example.domain.model.Posts
import com.example.domain.model.User
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
    private val getPostOwner: GetPostOwnerUseCase,
    private val getPostComments: GetCommentsUseCase,
    private val getPostDetail: GetPostDetailUseCase,
    private val markAsRead: MarkAsReadUseCase,
    private val markAsFav: FavPostsUseCase
) : ViewModel() {

    private val _fav = MutableLiveData<Boolean>()
    val fav: LiveData<Boolean>
        get() = _fav
    private var post: Posts? = null
    private val _post = MutableLiveData<Posts>()
    private val _user = MutableLiveData<User>()
    private val _comments = MutableLiveData<List<Comments>>()
    private val _infoWrapper = MediatorLiveData<InfoWrapper>()
    val info: LiveData<InfoWrapper>
        get() = _infoWrapper

    init {
        startWrappingData()
    }

    fun init(postId: String, user: String) {
        viewModelScope.launch {
            post = getPostDetail(postId)
            _post.postValue(post)
            post?.run { markAsRead(this) }
            _user.value = getPostOwner(user)
            _comments.postValue(getPostComments(postId))
        }
    }

    fun onFavClicked() {
        viewModelScope.launch {
            post?.run {
                if (favorite) {
                    _fav.value = false
                    markAsFav.unMark(this)
                } else {
                    _fav.value = true
                    markAsFav.mark(this)
                }
            }
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