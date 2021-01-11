package com.reddit.app.di

import android.content.Context
import com.reddit.app.data.repository.RemotePostToLocalDbRepository
import com.reddit.app.data.repository.SavePictureRepository
import com.reddit.app.data.source.LocalDataSource
import com.reddit.app.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    @Provides
    fun repositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) = RemotePostToLocalDbRepository(localDataSource, remoteDataSource)

    @Provides
    fun pictureRepositoryProvider(@ApplicationContext appContext: Context) =
            SavePictureRepository(appContext)
}