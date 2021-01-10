package com.reddit.app.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reddit.app.domain.model.Post
import com.reddit.app.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.launch

class PostListViewModel @ViewModelInject constructor(
    private val getPostsUseCase: GetPostsUseCase): ViewModel() {

    private val _redditList = MutableLiveData<List<Post>>()
    val redditList: LiveData<List<Post>> = _redditList

    init {
        viewModelScope.launch { getNextPosts() }
    }

    private suspend fun getNextPosts() {
        _redditList.value = getPostsUseCase.invoke()
    }
}