package com.reddit.app.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reddit.app.domain.model.Post
import com.reddit.app.domain.usecase.GetPostByIdUseCase
import kotlinx.coroutines.launch

class PostDetailViewModel @ViewModelInject constructor(
    private val getPostByIdUseCase: GetPostByIdUseCase) : ViewModel() {

    private val _postDetail = MutableLiveData<Post>()
    val postDetail: LiveData<Post> get() = _postDetail

    fun getDetail(id: String) {
        viewModelScope.launch {
            _postDetail.value = getPostByIdUseCase.invoke(id)
        }
    }
}