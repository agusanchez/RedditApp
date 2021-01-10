package com.reddit.app.data.server

import com.google.gson.annotations.SerializedName

data class RedditResponse (val data: RedditData)

data class RedditData (val children: List<Children>, val after: String)

data class Children(val data: Post)

data class Post(val id: String,
                val title: String,
                val thumbnail: String?,
                val created: Long?,
                val description: String?,
                @SerializedName(value = "url")
                val contentUrl: String?,
                @SerializedName(value = "num_comments")
                val comment: Int?,
                val author: String?,
                val iconUrl: String?)

