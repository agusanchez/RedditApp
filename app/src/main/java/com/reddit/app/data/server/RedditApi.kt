package com.reddit.app.data.server

import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {

    @GET("/top.json")
    suspend fun getPosts(@Query("limit") page: Int = 10,
                         @Query("after") after: String = ""
    ): RedditResponse
}