package com.reddit.app.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reddit.app.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
    }
}