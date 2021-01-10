package com.reddit.app.data.source

import com.reddit.app.domain.model.Post
import com.reddit.app.domain.model.RedditData

interface LocalDataSource {
    suspend fun size(): Int
    suspend fun savePosts(posts: List<Post>)
    suspend fun saveRedditData(data: RedditData)
    suspend fun getRedditData(): List<RedditData>
    suspend fun getPosts(): List<Post>
    suspend fun markPostAsRead(id: String)
    suspend fun dismissPost(id: String)
    suspend fun dismissAllPosts(ids: List<String>)
    suspend fun getPostById(id: String): Post
}