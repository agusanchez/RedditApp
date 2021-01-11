package com.reddit.app.mock

import com.reddit.app.domain.model.Post

val mockedPostList = listOf(
    Post("id1", "Title 1", "thumbnail 1", 0, "Description 1", "url 1", 0, "author 1", "iconUrl 1"),
    Post("id2", "Title 2", "thumbnail 2", 0, "Description 2", "url 2", 0, "author 2", "iconUrl 2"),
    Post("id3", "Title 3", "thumbnail 3", 0, "Description 3", "url 3", 0, "author 3", "iconUrl 3")
)

val mockedPost =
    Post("id1", "Title 1", "thumbnail 1", 0, "Description 1", "url 1", 0, "author 1", "iconUrl 1")
