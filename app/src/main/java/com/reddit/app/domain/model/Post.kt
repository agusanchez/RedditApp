package com.reddit.app.domain.model

data class Post(
        val id: String,
        val title: String,
        val thumbnail: String?,
        val created: Long?,
        val description: String?,
        val contentUrl: String?,
        val comment: Int?,
        val author: String?,
        val iconUrl: String?,
        val wasRead: Boolean = false,
        val isDismissed: Boolean = false
)
