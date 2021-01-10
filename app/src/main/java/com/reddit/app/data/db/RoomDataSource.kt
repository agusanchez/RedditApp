package com.reddit.app.data.db

import com.reddit.app.domain.model.Post
import com.reddit.app.data.source.LocalDataSource
import com.reddit.app.data.toDomainPost
import com.reddit.app.data.toDomainRedditData
import com.reddit.app.data.toRoomPost
import com.reddit.app.data.toRoomRedditData
import com.reddit.app.domain.model.RedditData
import com.reddit.app.data.db.RedditData as RoomRedditData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: RedditDatabase) : LocalDataSource {

    private val redditDao = db.redditDao()

    override suspend fun savePosts(posts: List<Post>) {
        withContext(Dispatchers.IO) { redditDao.insertPosts(posts.map { it.toRoomPost() }) }
    }

    override suspend fun getPosts(): List<Post> = withContext(Dispatchers.IO) {
        redditDao.getAllPosts().map { it.toDomainPost() }
    }

    override suspend fun markPostAsRead(id: String) {
        withContext(Dispatchers.IO) { redditDao.markPostAsRead(id) }
    }

    override suspend fun dismissPost(id: String) {
        withContext(Dispatchers.IO) { redditDao.dismissPost(id) }
    }

    override suspend fun dismissAllPosts(ids: List<String>) {
        withContext(Dispatchers.IO) { redditDao.dismissAllPosts(ids) }
    }

    override suspend fun saveRedditData(data: RedditData) {
        withContext(Dispatchers.IO) { redditDao.insertRedditData(data.toRoomRedditData()) }
    }

    override suspend fun getRedditData(): List<RedditData> =
        withContext(Dispatchers.IO) {
            redditDao.getRedditData()
                .map { redditData: RoomRedditData -> redditData.toDomainRedditData() }
    }

    override suspend fun size(): Int {
        return withContext(Dispatchers.IO) { redditDao.postsCount() }
    }
}