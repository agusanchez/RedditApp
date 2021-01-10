package com.reddit.app.data.server

import com.reddit.app.data.source.RemoteDataSource


class RedditDbDataSource : RemoteDataSource {
    override suspend fun getPosts(after: String): RedditResponse {
        return RestAdapter.service.getPosts(after = after)
    }
}