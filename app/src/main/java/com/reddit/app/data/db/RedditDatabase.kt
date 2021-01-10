package com.reddit.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Post::class, RedditData::class], version = 1)
abstract class RedditDatabase : RoomDatabase() {

    abstract fun redditDao(): RedditDao
}