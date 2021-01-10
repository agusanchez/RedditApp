package com.reddit.app.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Post (@PrimaryKey val id: String,
            val title: String,
            val thumbnail: String?,
            val created: Long?,
            val description: String?,
            val contentUrl: String?,
            val comment: Int?,
            val author: String?,
            val iconUrl: String?,
            val wasRead: Boolean,
            val isDismissed: Boolean)