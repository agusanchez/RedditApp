package com.reddit.app.data

import com.reddit.app.domain.model.Post
import com.reddit.app.data.server.Post as ServerPost

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