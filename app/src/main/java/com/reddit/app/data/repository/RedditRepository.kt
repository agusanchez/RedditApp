package com.reddit.app.data.repository

import com.reddit.app.domain.model.Post

interface RedditRepository {
    suspend fun getNextPosts(): List<Post>
}