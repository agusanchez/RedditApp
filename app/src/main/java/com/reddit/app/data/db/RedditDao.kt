package com.reddit.app.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RedditDao {

    @Query("SELECT * FROM Post WHERE isDismissed = 0")
    fun getAllPosts(): List<Post>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPosts(movies: List<Post>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRedditData(data: RedditData)

    @Query("SELECT * FROM RedditData")
    fun getRedditData(): List<RedditData>

    @Query("SELECT COUNT(id) FROM Post WHERE isDismissed = 0")
    suspend fun postsCount(): Int
}