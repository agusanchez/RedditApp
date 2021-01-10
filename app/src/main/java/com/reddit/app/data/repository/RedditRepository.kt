package com.reddit.app.data.repository

import com.reddit.app.domain.model.Post

interface RedditRepository {
    suspend fun getNextPosts(lastVisible: Int): List<Post>
}