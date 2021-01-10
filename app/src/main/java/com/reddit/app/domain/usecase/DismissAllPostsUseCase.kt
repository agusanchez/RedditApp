package com.reddit.app.domain.usecase

import com.reddit.app.data.repository.RemotePostToLocalDbRepository
import com.reddit.app.domain.model.Post

class DismissAllPostsUseCase(private val redditRepository: RemotePostToLocalDbRepository) {
    suspend fun invoke(posts: List<Post>) = redditRepository.dismissAllPosts(posts)
}