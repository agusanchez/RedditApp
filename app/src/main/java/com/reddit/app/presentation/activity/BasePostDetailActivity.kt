package com.reddit.app.presentation.activity

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.reddit.app.presentation.viewmodel.PostDetailViewModel

abstract class BasePostDetailActivity : AppCompatActivity() {

    companion object {
        const val POST_ID = "post_id"
    }

    private val viewModel: PostDetailViewModel by viewModels()

    fun showPostDetail(postId: String) {
        viewModel.getDetail(postId)
    }
}