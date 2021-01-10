package com.reddit.app.data.repository

import com.reddit.app.data.server.Children
import com.reddit.app.data.source.RemoteDataSource
import com.reddit.app.data.toDomainPost
import com.reddit.app.domain.model.Post

class RemotePostToLocalDbRepository(private val remoteDataSource: RemoteDataSource)
    : RedditRepository {

    override suspend fun getNextPosts(): List<Post> {
        val redditResponse = remoteDataSource.getPosts(after = "")
        return redditResponse.data.children.map { children: Children ->
            children.data.toDomainPost() }
    }
}