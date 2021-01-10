package com.reddit.app.presentation.activity

import com.reddit.app.data.repository.RemotePostToLocalDbRepository
import com.reddit.app.domain.usecase.GetPostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class PostListActivityModule {

    @Provides
    fun getRemotePostProvider(redditRepository: RemotePostToLocalDbRepository) =
        GetPostsUseCase(redditRepository)
}