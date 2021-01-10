package com.reddit.app.presentation.activity

import android.os.Bundle
import com.reddit.app.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailActivity : BasePostDetailActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        val postId = intent?.extras?.getString(POST_ID,"").orEmpty()
        showPostDetail(postId)
    }
}