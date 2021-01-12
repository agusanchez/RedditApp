package com.reddit.app.presentation.activity

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.viewModels
import com.reddit.app.R
import com.reddit.app.presentation.viewmodel.PostListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostListActivity: BasePostDetailActivity() {

    private val viewModel: PostListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        viewModel.postClicked.observe(this, { postId ->
            if (isScreenOrientationPortrait()) {
                showDetailPostFragment(postId)
            } else {
                showPostDetail(postId)
            }
        })
    }

    private fun showDetailPostFragment(postId: String) {
        val intent = Intent(this, PostDetailActivity::class.java)
        intent.putExtra(POST_ID, postId)
        startActivity(intent)
    }

    private fun isScreenOrientationPortrait() =
            resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
}