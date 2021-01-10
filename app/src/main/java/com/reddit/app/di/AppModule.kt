package com.reddit.app.di

import android.app.Application
import androidx.room.Room
import com.reddit.app.data.db.RedditDatabase
import com.reddit.app.data.db.RoomDataSource
import com.reddit.app.data.server.RedditDbDataSource
import com.reddit.app.data.source.LocalDataSource
import com.reddit.app.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application): RedditDatabase = Room.databaseBuilder(
        app, RedditDatabase::class.java, "reddit-db"
    ).build()

    @Provides
    fun localDataSourceProvider(db: RedditDatabase): LocalDataSource = RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = RedditDbDataSource()
}