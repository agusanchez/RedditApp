package com.reddit.app.data.repository

import com.reddit.app.domain.model.Post

interface RedditRepository {
    suspend fun getNextPosts(lastVisible: Int): List<Post>
    suspend fun dismissPost(id: String): List<Post>
    suspend fun markPostAsRead(id: String): List<Post>
    suspend fun dismissAllPosts(ids: List<Post>)
}