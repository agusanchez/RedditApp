package com.reddit.app.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reddit.app.domain.model.Post
import com.reddit.app.domain.usecase.GetPostByIdUseCase
import com.reddit.app.domain.usecase.SavePictureUseCase
import com.reddit.app.utils.Status
import kotlinx.coroutines.launch
import java.lang.Exception

class PostDetailViewModel @ViewModelInject constructor(
        private val savePictureUseCase: SavePictureUseCase,
        private val getPostByIdUseCase: GetPostByIdUseCase) : ViewModel() {

    private val _postDetail = MutableLiveData<Post>()
    private val _pictureSavedStatus = MutableLiveData<Boolean>()
    val postDetail: LiveData<Post> get() = _postDetail
    val pictureSavedStatus: LiveData<Boolean> = _pictureSavedStatus

    fun getDetail(id: String) {
        viewModelScope.launch {
            _postDetail.value = getPostByIdUseCase.invoke(id)
        }
    }

    fun savePictureOnGallery(url: String) {
        viewModelScope.launch {
            try {
                savePictureUseCase.invoke(url)
                handleStatus(Status.SUCCESS)
            } catch (e: Exception) {
                handleStatus(Status.ERROR)
            }
        }
    }

    private fun handleStatus(status: Status) = when (status) {
        Status.ERROR -> {
            _pictureSavedStatus.value = false
        }
        Status.SUCCESS -> {
            _pictureSavedStatus.value = true
        }
        else -> _pictureSavedStatus.value = false
    }
}