package com.reddit.app.data

import com.reddit.app.domain.model.Post
import com.reddit.app.domain.model.RedditData
import com.reddit.app.data.server.Post as ServerPost
import com.reddit.app.data.db.Post as RoomPost
import com.reddit.app.data.db.RedditData as RoomRedditData
import com.reddit.app.data.server.RedditData as ServerRedditData

fun ServerPost.toDomainPost(): Post =
    Post(
            id,
            title,
            thumbnail,
            created,
            description,
            contentUrl,
            comment,
            author,
            iconUrl)


fun Post.toRoomPost(): RoomPost =
        RoomPost(
                id,
                title,
                thumbnail,
                created,
                description,
                contentUrl,
                comment,
                author,
                iconUrl,
                wasRead,
                isDismissed)

fun RoomPost.toDomainPost(): Post = Post(
        id,
        title,
        thumbnail,
        created,
        description,
        contentUrl,
        comment,
        author,
        iconUrl,
        wasRead,
        isDismissed)

fun ServerRedditData.toDomainRedditData(): RedditData =
        RedditData(after)

fun RedditData.toRoomRedditData(): RoomRedditData =
        RoomRedditData(after)

fun RoomRedditData.toDomainRedditData(): RedditData =
        RedditData(after)