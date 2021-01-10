package com.reddit.app.data.repository

import com.reddit.app.data.server.Children
import com.reddit.app.data.source.LocalDataSource
import com.reddit.app.data.source.RemoteDataSource
import com.reddit.app.data.toDomainPost
import com.reddit.app.data.toDomainRedditData
import com.reddit.app.domain.model.Post
import java.lang.Exception

class RemotePostToLocalDbRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource)
    : RedditRepository {

    override suspend fun getNextPosts(lastVisible: Int): List<Post> {
        val postListSize = localDataSource.size()
        if (checkPostListBelowMaxSize(postListSize) && checkThresholdIsVisible(lastVisible, postListSize)) {
            val redditResponse = remoteDataSource.getPosts(getRedditAfterData())
            localDataSource.saveRedditData(redditResponse.data.toDomainRedditData())
            localDataSource.savePosts(redditResponse.data.children.map { children: Children ->
                children.data.toDomainPost() })
        }
        return localDataSource.getPosts()
    }

    override suspend fun dismissPost(id: String): List<Post> {
        localDataSource.dismissPost(id)
        return localDataSource.getPosts()
    }

    override suspend fun markPostAsRead(id: String): List<Post> {
        localDataSource.markPostAsRead(id)
        return localDataSource.getPosts()
    }

    override suspend fun dismissAllPosts(ids: List<Post>) {
        localDataSource.dismissAllPosts(ids.map { it.id })
    }

    private suspend fun getRedditAfterData(): String = try {
        localDataSource.getRedditData().last().after
    } catch (e: Exception) { "" }

    private fun checkPostListBelowMaxSize(size: Int) = size < MAX_POSTS

    private fun checkThresholdIsVisible(lastVisible: Int, postListSize: Int) =
        lastVisible >= postListSize - PAGE_THRESHOLD

    companion object {
        private const val MAX_POSTS = 50
        private const val PAGE_THRESHOLD = 5
    }
}