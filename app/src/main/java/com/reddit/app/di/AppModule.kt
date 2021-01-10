package com.reddit.app.di

import com.reddit.app.data.server.RedditDbDataSource
import com.reddit.app.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = RedditDbDataSource()
}