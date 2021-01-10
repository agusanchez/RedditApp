package com.reddit.app.di

import com.reddit.app.data.repository.RemotePostToLocalDbRepository
import com.reddit.app.data.source.LocalDataSource
import com.reddit.app.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    @Provides
    fun repositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) = RemotePostToLocalDbRepository(localDataSource, remoteDataSource)
}