package com.reddit.app.presentation.viewholder

interface PostActionListener {
    fun onDismissedPost(id: String)
    fun onPostClicked(id: String)
}