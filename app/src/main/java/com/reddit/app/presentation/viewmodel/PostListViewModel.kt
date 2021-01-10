package com.reddit.app.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reddit.app.domain.model.Post
import com.reddit.app.domain.usecase.DismissAllPostsUseCase
import com.reddit.app.domain.usecase.DismissedPostUseCase
import com.reddit.app.domain.usecase.GetPostsUseCase
import com.reddit.app.domain.usecase.MarkPostAsReadUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostListViewModel @ViewModelInject constructor(
        private val getPostsUseCase: GetPostsUseCase,
        private val dismissPostUseCase: DismissedPostUseCase,
        private val markPostAsReadUseCase: MarkPostAsReadUseCase,
        private val dismissAllPostsUseCase: DismissAllPostsUseCase): ViewModel() {

    private val _redditList = MutableLiveData<List<Post>>()
    private val _spinner = MutableStateFlow(true)
    private val _lastVisible = MutableStateFlow(0)
    private val _isRefreshing = MutableLiveData<Boolean>()
    val redditList: LiveData<List<Post>> = _redditList
    val spinner: StateFlow<Boolean> = _spinner
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    val lastVisible = _lastVisible


    init {
        viewModelScope.launch { _lastVisible.collect { getNextPosts() } }
    }

    fun refreshForNewPosts () {
        viewModelScope.launch {
            _redditList.value = getPostsUseCase.invoke(_lastVisible.value)
            _isRefreshing.value = false
        }
    }

    private suspend fun getNextPosts() {
        _redditList.value = getPostsUseCase.invoke(_lastVisible.value)
        _spinner.value = false
    }

    fun dismissPost(id: String) {
        viewModelScope.launch {
            _redditList.value = dismissPostUseCase.invoke(id)
        }
    }

    fun postClicked(id: String) {
        viewModelScope.launch {
            _redditList.value = markPostAsReadUseCase.invoke(id)
        }
    }

    fun dismissAllPosts(postList: List<Post>) {
        viewModelScope.launch {
            dismissAllPostsUseCase.invoke(postList)
        }
    }
}