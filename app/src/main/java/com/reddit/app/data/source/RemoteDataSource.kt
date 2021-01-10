package com.reddit.app.data.source

import com.reddit.app.data.server.RedditResponse

interface RemoteDataSource {
    suspend fun getPosts(after: String): RedditResponse
}