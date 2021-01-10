package com.reddit.app.presentation.activity

import com.reddit.app.data.repository.RemotePostToLocalDbRepository
import com.reddit.app.domain.usecase.DismissAllPostsUseCase
import com.reddit.app.domain.usecase.DismissedPostUseCase
import com.reddit.app.domain.usecase.GetPostsUseCase
import com.reddit.app.domain.usecase.MarkPostAsReadUseCase
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

    @Provides
    fun dismissedPostProvider(redditRepository: RemotePostToLocalDbRepository) =
            DismissedPostUseCase(redditRepository)

    @Provides
    fun markPostAsReadProvider(redditRepository: RemotePostToLocalDbRepository) =
            MarkPostAsReadUseCase(redditRepository)

    @Provides
    fun dismissAllPostsProvider(redditRepository: RemotePostToLocalDbRepository) =
            DismissAllPostsUseCase(redditRepository)
}